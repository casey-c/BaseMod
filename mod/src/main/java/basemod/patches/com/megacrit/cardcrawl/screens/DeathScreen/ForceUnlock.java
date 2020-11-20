package basemod.patches.com.megacrit.cardcrawl.screens.DeathScreen;

import basemod.DevConsole;
import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.screens.DeathScreen;
import com.megacrit.cardcrawl.screens.GameOverScreen;
import com.megacrit.cardcrawl.unlock.AbstractUnlock;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import java.util.ArrayList;

@SpirePatch(
		clz = GameOverScreen.class,
		method="calculateUnlockProgress"
)
public class ForceUnlock {
	public static void Postfix(GameOverScreen instance) {
		System.out.println("Managing unlocks");
		if (DevConsole.forceUnlocks) {
			if (instance instanceof DeathScreen) {
				DeathScreen screen = (DeathScreen)instance;

				System.out.println("Attempting to force unlock");
				int unlockLevel = DevConsole.unlockLevel;

				screen.unlockBundle = UnlockTracker.getUnlockBundle(
						AbstractDungeon.player.chosenClass,
						(unlockLevel != -1) ? unlockLevel : UnlockTracker.getUnlockLevel(AbstractDungeon.player.chosenClass));
				if (screen.unlockBundle.size() == 0) {
					screen.unlockBundle = null;
					screen.returnButton.appear(Settings.WIDTH / 2.0F, Settings.HEIGHT * 0.15F, DeathScreen.TEXT[40]);
				}
			}

		}
	}
}
