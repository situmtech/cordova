package es.situm.plugin;

import android.content.Context;
import android.os.Build;
import android.speech.tts.TextToSpeech;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Locale;

public class TextToSpeechManager implements TextToSpeech.OnInitListener {

    private static final float DEFAULT_SPEECH_RATE_VALUE = 1.0f;
    private TextToSpeech textToSpeech;
    private boolean canSpeak = true;

    public TextToSpeechManager(Context context) {
        String enginePackage = getPreferredTtsEnginePackage(context);
        if (enginePackage != null) {
            textToSpeech = new TextToSpeech(context, this, enginePackage);
        } else {
            textToSpeech = new TextToSpeech(context, this);
        }
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            // Establece el idioma (opcional)
            textToSpeech.setLanguage(Locale.getDefault());
        }
    }

    public void speak(JSONObject arguments) {
        if (!canSpeak) return;
        if (!arguments.has("text")) return;

        if (arguments.has("lang")) {
            try {
                String lang = arguments.getString("lang");
                textToSpeech.setLanguage(new Locale(lang));
            } catch (JSONException e) {
                e.printStackTrace();
                // DO NOTHING: use the current language.
            }
        }

        if (arguments.has("pitch")) {
            try {
                float pitch = (float) arguments.getDouble("pitch");
                textToSpeech.setPitch(pitch);
            } catch (JSONException e) {
                e.printStackTrace();
                // DO NOTHING: use the current pitch.
            }
        }

        if (arguments.has("rate")) {
            try {
                float rate = (float) arguments.getDouble("rate");
                textToSpeech.setSpeechRate(convertToAndroidSpeechRate(rate));
            } catch (JSONException e) {
                e.printStackTrace();
                // DO NOTHING: use the current rate.
            }
        }

        try {
            String text = arguments.getString("text");
            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // ui.speak_aloud_text message sends the speech rate in FlutterTts scale [0.0f,1.0f],
    // so convert it to the Android scale [0.0f,2.0f].
    private float convertToAndroidSpeechRate(float value) {
        if (value < 0 || value > 1.0f) {
            return DEFAULT_SPEECH_RATE_VALUE;
        }
        return 2 * value;
    }

    public void stop() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }

    private String getPreferredTtsEnginePackage(Context context) {
        TextToSpeech ttsTemp = new TextToSpeech(context, status -> {});
        List<TextToSpeech.EngineInfo> engines = ttsTemp.getEngines();
        ttsTemp.shutdown();

        String googleTts = "com.google.android.tts";
        if (Build.MANUFACTURER.equalsIgnoreCase("Samsung")) {
            for (TextToSpeech.EngineInfo engine : engines) {
                if (googleTts.equals(engine.name)) {
                    return googleTts;
                }
            }
        }
        return null;
    }

    public void setCanSpeak(boolean value) {
        // TTS is paused when screen is off, app goes background and MapView gets destroyed
        canSpeak = value;
        if (!canSpeak && textToSpeech != null) {
            textToSpeech.stop();
        }
    }
}
