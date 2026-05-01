import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class PianoSequenceGenerator {

    public static String generatePianoSequence(String s,String emotion, String duration) {
        String prompte = """
                You are a piano note generator. You output ONLY one line of piano notes. 
                NEVER write explanations, greetings, or any text. Only the sequence.

                Allowed keys (uppercase):
                F2 F#2 G2 G#2 A2 A#2 B2
                C3 C#3 D3 D#3 E3
                F3 F#3 G3 G#3 A3 A#3 B3
                C4 C#4 D4 D#4 E4
                F4 F#4 G4 G#4 A4 A#4 B4
                C5 C#5 D5 D#5 E5

                Rules:
                1. NEVER list keys in order; produce a real melody.
                2. Use "." for duration:
                   . = 200 ms
                   .. = 400 ms
                   ... = 600 ms
                   ..... = 1 second
                   Mix durations to create rhythm.
                3. Sharps (#) MUST appear naturally.
                4. Only one line. No extra symbols, text, or formatting.
                5. Duration and style must follow the emotion requested.

                Examples (learn the style):
                A3..G#3...C4.....F3..A#3...D4..
                F4...C#4..A3.....G3..E4...B3....
                G2..A#2....D3..F#3...C4..G4....

                Emotion: %s
                Duration: %s

                
                """.formatted(emotion, duration);

          String prompt=prompte.concat(s);

        try {
            HttpClient client = HttpClient.newHttpClient();
            String jsonPayload = String.format("{\"model\":\"llama2\",\"prompt\":%s,\"stream\":false}", 
                                               escapeJson(prompt));

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:11434/api/generate"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonPayload, StandardCharsets.UTF_8))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Simple parsing: find "response":"<sequence>"
            String body = response.body();
            int start = body.indexOf("\"response\":\"") + 12;
            int end = body.indexOf("\"", start);
            if (start > 11 && end > start) {
                return body.substring(start, end);
            } else {
                return "Error: Could not parse response";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }

    // Escape newlines and quotes for JSON string
    private static String escapeJson(String str) {
        return "\"" + str.replace("\\", "\\\\").replace("\"", "\\\"").replace("\n", "\\n") + "\"";
    }

    // public static void main(String[] args) {
    //     String emotion = "happy motivational";
    //     String duration = "60-second sequence";

    //     String sequence = generatePianoSequence(emotion, duration);
    //     System.out.println(sequence);
    // }
}
