package app.template.extension;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import java.util.Random;

public class ReelsToastHelper {

    private static final String[] MESSAGES = {
        "Crotte de pigeon. Vas pas ici.",
        "Tu vaux mieux que ça.",
        "Je veux pas être boomer. Mais va toucher de l'herbe.",
        "Va manger des haricots verts.",
        "Vas boire de l'eau au moins.",
        "Tu as installé un truc pour pas faire ça. Et tu le fais quand même.",
        "Tu as déverrouillé ton téléphone 23 fois aujourd'hui. Cherche pas pourquoi.",
        "Quelqu'un t'a mis ce message exprès. Tu vois qui c'est.",
        "T'es chiante.",
        "Cet algorithme te connaît mieux que ta mère.",
        "Appuie sur retour. Ose.",
        "Batterie cérébrale : 3%",
        "Connexion à la réalité perdue depuis 47 minutes.",
        "T'as vraiment rien de mieux à faire hein.",
        "Ptn lâche ces reels. Arrête de Scroll.",
        "Tu pourrais être en train de faire quelque chose de ta vie."
    };

    private static final Random RANDOM = new Random();

    public static void showToast(Context context) {
        if (context == null) return;
        final String message = MESSAGES[RANDOM.nextInt(MESSAGES.length)];
        if (Looper.myLooper() == Looper.getMainLooper()) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        } else {
            new Handler(Looper.getMainLooper()).post(() ->
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            );
        }
    }
}
