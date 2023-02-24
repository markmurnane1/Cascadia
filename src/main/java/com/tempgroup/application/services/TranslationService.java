// package com.tempgroup.application.services;

// import java.io.FileInputStream;
// import java.io.FileReader;
// import java.io.InputStream;
// import java.util.Map;

// import com.google.gson.Gson;

// public class TranslationService {
// private Map<String, Object> loadedTranslation;

// public TranslationService() {
// try {
// FileReader reader = new FileReader("en.json");
// Gson gson = new Gson();
// this.loadedTranslation = gson.fromJson(reader, Map.class);
// } catch (Exception e) {
// }
// }

// public String t(String key) {
// this.loadedTranslation.get(key);
// }

// // write a function which reads in the en.json file and makes
// // it's keys accessible by strings, like "SCREENS.GAME_SETUP.NUM_PLAYERS"
// }
