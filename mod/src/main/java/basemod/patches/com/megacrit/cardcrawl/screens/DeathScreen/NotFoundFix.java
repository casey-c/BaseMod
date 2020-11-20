package basemod.patches.com.megacrit.cardcrawl.screens.DeathScreen;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.screens.DeathScreen;
import com.megacrit.cardcrawl.screens.GameOverScreen;

@SpirePatch(
		clz = GameOverScreen.class,
		method = "calculateUnlockProgress"
)
public class NotFoundFix {
	public static void Postfix(GameOverScreen instance) {
		if (instance instanceof DeathScreen) {
			DeathScreen screen = (DeathScreen) instance;
			if (screen.unlockBundle != null && screen.unlockBundle.size() <= 0) {
				// game checks for null, not for wrong size
				screen.unlockBundle = null;
			}

		}
	}
}
