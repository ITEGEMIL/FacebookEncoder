package pl.kurs.java;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FacebookService {
    private List<Message> messages;
    private List<Word> words;
    private FileService fileService;
    private ObjectMapper objectMapper;

    public FacebookService(String path) throws IOException {
        objectMapper = new ObjectMapper();
        fileService = new FileService();
        List<File> files = fileService.getAllJsonFilesFromPath(path);
        words = new ArrayList<>();
        messages = new ArrayList<>();
        getMessagesFromFiles(files);
        getWords();
    }

    private void getWords() {
        for (Message message : messages) {
            if (message != null && message.getContent() != null) {
                String[] split = message.getContent().split("\\s+");
                for (String s : split) {
                    words.add(new Word(s, message.getSenderName()));
                }
            }
        }
    }

    private void getMessagesFromFiles(List<File> files) throws IOException {
        for (File file : files) {
            String json = correctEncoding(Files.readString(file.toPath()));
            Root root = objectMapper.readValue(json, Root.class);
            messages.addAll(root.getMessages());
        }
        // messages.stream().map(Message::getContent).forEach(System.out::println);
    }

    private String correctEncoding(String readString) {
        return readString
                .replaceAll("\\\\u00c5\\\\u00bc", "ż")
                .replaceAll("\\\\u00c5\\\\u0082", "ł")
                .replaceAll("\\\\u00c4\\\\u0087", "ć")
                .replaceAll("\\\\u00c5\\\\u0084", "ń")
                .replaceAll("\\\\u00c4\\\\u0085", "ą")
                .replaceAll("\\\\u00c5\\\\u009b", "ś")
                .replaceAll("\\\\u00c4\\\\u0099", "ę")
                .replaceAll("\\\\u00c5\\\\u00ba", "ź")
                .replaceAll("\\\\u00c3\\\\u00b3", "ó")
                .replaceAll("\\\\u00e2\\\\u0080\\\\u009e", "„")
                .replaceAll("\\\\u00e2\\\\u0080\\\\u009d", "”")
                .replaceAll("\\\\u00c5\\\\u00bb", "Ż")
                .replaceAll("\\\\u00f0\\\\u009f\\\\u0098\\\\u008d", "Smiling_Face_with_Heart-Eyes ") // 😍
                .replaceAll("\\\\u00f0\\\\u009f\\\\u0098\\\\u009e", "Disappointed_Face ") // 😞
                .replaceAll("\\\\u00f0\\\\u009f\\\\u00a6\\\\u008b", "Red_Heart ") // ❤️
                .replaceAll("\\\\u00f0\\\\u009f\\\\u0098\\\\u00b6", "Face_Without_Mouth ") //😶
                .replaceAll("\\\\u00f0\\\\u009f\\\\u0098\\\\u0082", "Face_with_Tears_of_Joy ") // 😂
                .replaceAll("\\\\u00f0\\\\u009f\\\\u00a5\\\\u00b0", "Smiling_Face_with_Hearts ") //🥰
                .replaceAll("\\\\u00f0\\\\u009f\\\\u0098\\\\u0081", "Beaming_Face_with_Smiling_Eyes ") //😁
                .replaceAll("\\\\u00f0\\\\u009f\\\\u0098\\\\u008a", "Smiling_Face_with_Smiling_Eyes ");//😊
    }

    public Map<String, Long> countMessagePerPerson() {
        return messages.stream()
                .map(Message::getSenderName)
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()));
    }

    public Map<String, Long> countWordsPerPerson() {
        return words.stream()
                .map(Word::getSender)
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()));
    }

    public Map<String, String> mostCommonWordPerPerson() {
        Map<String, String> mostCommonWordPerPersonMap = new HashMap<>();

        Map<String, List<String>> wordByPerson = new HashMap<>();
        for (Word word : words) {
            String sender = word.getSender();
            List<String> strings = wordByPerson.get(sender);
            if (strings == null) {
                wordByPerson.put(sender, new ArrayList<>(List.of(word.getContent())));
            } else {
                strings.add(word.getContent());
                wordByPerson.put(sender, strings);
            }
        }

        for (Map.Entry<String, List<String>> entry : wordByPerson.entrySet()) {
            String sender = entry.getKey();
            List<String> sendersWords = entry.getValue();

            Map<String, Long> wordsCounts = sendersWords.stream()
                    .collect(Collectors.groupingBy(w -> w, Collectors.counting()));
            String mostCommonWord = wordsCounts.entrySet().stream()
                    .max(Comparator.comparingLong(Map.Entry::getValue))
                    .map(Map.Entry::getKey)
                    .orElse(null);
            mostCommonWordPerPersonMap.put(sender, mostCommonWord);

//            Map<String, Long> wordsCounts = new HashMap<>();
//            for (String w : sendersWords) {
//                wordsCounts.merge(w, 1L, Long::sum);
//            }
//
//            Map.Entry<String, Long> maxValueEntry = null;
//            for (Map.Entry<String, Long> entry1 : wordsCounts.entrySet()) {
//                if (maxValueEntry == null || maxValueEntry.getValue() < entry1.getValue()) {
//                    maxValueEntry = entry1;
//                }
//            }
//
//            String maxValueEntryKey = maxValueEntry.getKey();

//            mostCommonWordPerPersonMap.put(sender, maxValueEntryKey);
        }

        return mostCommonWordPerPersonMap;
    }

}

