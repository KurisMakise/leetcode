package solution;

import lombok.Data;
import org.springframework.util.DigestUtils;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

/**
 * @author violet
 * @version 1.0
 * @date 2021/2/28 23:35
 */
public class Code1116 {
    private int n;

    private ReentrantLock lock = new ReentrantLock();
    private AtomicInteger flag = new AtomicInteger(0);
    private Condition conditionZero = lock.newCondition();
    private Condition conditionEven = lock.newCondition();
    private Condition conditionOdd = lock.newCondition();


    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        lock.lock();
        try {
            while (flag.get() != 0 || flag.get() != 10) {
                conditionZero.await();
            }
            printNumber.accept(n);
            if (flag.get() == 0) {
                flag.set(1);
                conditionOdd.signal();
            } else if (flag.get() == 10) {
                flag.set(2);
                conditionEven.signal();
            }
        } finally {
            lock.unlock();
        }
    }


    private String md5(String filePath) {
        try {
            File from = new File(filePath);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            InputStream inputStream = new FileInputStream(from);
            int len1;
            byte[] bytes1 = new byte[1024];
            while ((len1 = inputStream.read(bytes1)) > 0) {
                byteArrayOutputStream.write(bytes1, 0, len1);
            }

            return DigestUtils.md5DigestAsHex(byteArrayOutputStream.toByteArray());

        } catch (Exception e) {

        }
        return "";
    }

    /**
     * 42b4b8b15d03c660b73e3eef4e1eb690
     *
     * @param args
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        Code1116 code1116 = new Code1116();
        System.out.println(Arrays.toString(code1116.reversePrint(head)));
    }

    public int[] reversePrint(ListNode head) {
        head = recur(null, head);
        int[] res = new int[size];
        for (int i = 0; i < res.length; i++) {
            res[i] = head.val;
            head = head.next;
        }
        return res;
    }

    int size = 0;

    ListNode recur(ListNode pre, ListNode cur) {
        if (cur == null) {
            return pre;
        }
        size++;
        ListNode next = cur.next;
        cur.next = pre;
        return recur(cur, next);
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public void start(String filePath) throws IOException, NoSuchAlgorithmException {
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        File from = new File(filePath);

        String md5 = md5(filePath);
        System.out.println(md5);
        int chunkSize = 1;

        for (int i = 0; i < 20; i++) {
            String id = UUID.randomUUID().toString();
            int index = 0;

            FileChannel fileChannel = new FileInputStream(from).getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(chunkSize);
            int len;

            while ((len = fileChannel.read(buffer)) > 0) {
                buffer.flip();

                UploadInfo uploadInfo = new UploadInfo();
                uploadInfo.setFileName(from.getName());
                uploadInfo.setUploadId(id);
                uploadInfo.setChunkSize(chunkSize);
                uploadInfo.setIndex(index++);
                uploadInfo.setLen(len);
                uploadInfo.setLength(fileChannel.size());
                uploadInfo.setMd5(md5);
                byte[] bytes = new byte[buffer.limit()];
                buffer.get(bytes);
                uploadInfo.setBytes(bytes);
                uploadInfo.setInputStream(new ByteArrayInputStream(bytes, 0, buffer.limit()));
                executorService.execute(() -> {
                    try {
                        upload(uploadInfo);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

                buffer.clear();
            }
        }
        executorService.shutdown();

    }

    public void upload(UploadInfo uploadInfo) throws IOException {
        FileChannel channel = new RandomAccessFile(getFile(uploadInfo), "rw").getChannel();

//        ReadableByteChannel outChannel = Channels.newChannel(uploadInfo.inputStream);
//        channel.transferFrom(outChannel, uploadInfo.getSeek(), uploadInfo.getLen());
        MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, uploadInfo.getSeek(), uploadInfo.getLen());
        map.put(uploadInfo.getBytes());
//        map.force();
        channel.close();
        map.clear();


        if (uploadInfo.getSeek() + uploadInfo.getLen() == uploadInfo.length) {
            FileInputStream inputStream = new FileInputStream(getFile(uploadInfo));
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int len;
            byte[] bytes = new byte[1024];
            while ((len = inputStream.read(bytes)) > 0) {
                byteArrayOutputStream.write(bytes, 0, len);
            }

            String md5 = DigestUtils.md5DigestAsHex(byteArrayOutputStream.toByteArray());
            if (!md5.equals(uploadInfo.md5)) {
                System.out.println(getFile(uploadInfo).getAbsoluteFile() + " id:" + uploadInfo.getUploadId() + "new md5:" + md5 + " original md5:" + uploadInfo.md5);
            }
        }
    }

    private final Map<String, File> filePathMap = new ConcurrentHashMap<>();

    private File getFile(UploadInfo uploadInfo) {
        //获取文件地址
        return filePathMap.computeIfAbsent(uploadInfo.getUploadId(), s -> generateFilePath(uploadInfo));
    }

    public static void io(File from, File to) throws IOException {
        FileInputStream inputStream = new FileInputStream(from);

        FileOutputStream outputStream = new FileOutputStream(to);

        byte[] bytes = new byte[1024];
        int len;
        while ((len = inputStream.read(bytes)) > 0) {
            outputStream.write(bytes, 0, len);
        }

        inputStream.close();
        outputStream.close();
    }

    /**
     * 例：E:/rootDir/uploadId/fileName.xml
     *
     * @param uploadInfo 文件信息
     * @return 文件路径
     */
    protected File generateFilePath(UploadInfo uploadInfo) {
        String filePath = "E:/upload" + File.separator + uploadInfo.getUploadId();

        File folder = new File(filePath);
        if (!folder.exists()) {
            if (!folder.mkdirs()) {
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            }
        }
        File file = new File(filePath + File.separator + uploadInfo.getFileName());
        if (!file.exists()) {
            try {
                if (!file.createNewFile()) {
                    System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                } else {
                    RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rwd");
                    randomAccessFile.setLength(uploadInfo.getLength());
                    randomAccessFile.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    public static void nio(File from, File to) throws IOException {
        FileChannel input = new FileInputStream(from).getChannel();
        FileChannel output = new FileOutputStream(to).getChannel();

        input.transferTo(0, input.size(), output);
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        lock.lock();

        try {
            while (flag.get() != 1) {
                conditionOdd.await();
            }
            printNumber.accept(n);
            flag.set(0);
            conditionZero.signal();
        } finally {
            lock.unlock();
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        lock.lock();

        try {
            while (flag.get() != 2) {
                conditionEven.await();
            }
            printNumber.accept(n);
            flag.set(10);
            conditionZero.signal();
        } finally {
            lock.unlock();
        }
    }

    @Data
    public static class UploadInfo {
        /**
         * 上传id
         */
        private String uploadId;
        /**
         * 上传内容
         */
        private InputStream inputStream;
        /**
         * 文件名称
         */
        private String fileName;
        /**
         * 分片大小
         */
        private long chunkSize;
        /**
         * 第几个分片
         */
        private int index;

        /**
         * 文件大小
         */
        private long length;
        /**
         * 文件md5
         */
        private String md5;

        private int len;
        private byte[] bytes;


        public Long getSeek() {
            return chunkSize * index;
        }
    }
}
