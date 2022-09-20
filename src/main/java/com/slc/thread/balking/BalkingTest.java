package com.slc.thread.balking;

import javax.print.Doc;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class BalkingTest {

    static class Document {
        private boolean changed = false;
        private List<String> content = new ArrayList<>();
        private final FileWriter writer;
        private static AutoSaveThread autoSaveThread;

        private Document(String documentPath, String documentName) throws IOException {
            this.writer = new FileWriter(new File(documentPath, documentName));
        }

        public static Document create(String documentPath, String documentName) throws IOException {
            Document document = new Document(documentPath, documentName);
            autoSaveThread = new AutoSaveThread(document);
            autoSaveThread.start();
            return document;
        }

        public void edit(String content) {
            synchronized (this) {
                this.content.add(content);
                this.changed = true;
            }
        }

        public void close() throws IOException {
            autoSaveThread.interrupt();
            writer.close();

        }

        public void save() throws IOException {
            synchronized (this) {
                if (!changed) {
                    return;
                }
                System.out.println(Thread.currentThread() + " execute the save action");
                Iterator<String> iterator = content.iterator();
                while (iterator.hasNext()){
                    String cacheLine=iterator.next();
                    this.writer.write(cacheLine);
                    this.writer.write("\r\n");
                    this.writer.flush();
                    this.changed = false;
                    iterator.remove();
                }
            }
        }
    }

    static class AutoSaveThread extends Thread {
        private final Document document;

        public AutoSaveThread(Document document) {
            super("DocumentAutoSaveThread");
            this.document = document;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    document.save();
                    TimeUnit.SECONDS.sleep(10);
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class DocumentEditThread extends Thread {
        private final String documentPath;
        private final String documentName;
        private final Scanner scanner = new Scanner(System.in);

        public DocumentEditThread(String documentPath, String documentName) {
            super("DocumentEditThread");
            this.documentPath = documentPath;
            this.documentName = documentName;
        }

        @Override
        public void run() {
            int times = 0;
            try {
                Document document = Document.create(documentPath, documentName);
                while (true) {
                    String text = scanner.next();
                    if ("quit".equals(text)) {
                        document.close();
                        break;
                    }
                    document.edit(text);
                    if (times == 5) {
                        document.save();
                        times = 0;
                    }
                    times++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new DocumentEditThread("C:\\develop\\idea_workspace\\thread","a.txt").start();
        Object obj=new Object();

    }
}
