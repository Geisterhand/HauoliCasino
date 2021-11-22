package eu.mccluster.hauolicasino.menu;

import ca.landonjw.gooeylibs2.api.button.Button;
import ca.landonjw.gooeylibs2.api.button.GooeyButton;
import ca.landonjw.gooeylibs2.api.data.UpdateEmitter;
import ca.landonjw.gooeylibs2.api.page.Page;
import ca.landonjw.gooeylibs2.api.template.Template;
import ca.landonjw.gooeylibs2.api.template.types.ChestTemplate;
import eu.mccluster.hauolicasino.ConfigManagement;
import eu.mccluster.hauolicasino.HauoliCasino;
import eu.mccluster.hauolicasino.config.scratchcard.ScratchCardGeneralConfig;
import eu.mccluster.hauolicasino.config.scratchcard.ScratchCardLang;
import eu.mccluster.hauolicasino.menu.CustomButtons.ScratchCardButton;
import eu.mccluster.hauolicasino.utils.EconomyUtils;
import eu.mccluster.hauolicasino.utils.ItemUtils;
import eu.mccluster.hauolicasino.utils.TextUtils;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import java.io.File;
import java.nio.file.Paths;

public class ScratchCardInventory extends UpdateEmitter<Page> implements Page {

    ScratchCardGeneralConfig _config = ConfigManagement.getInstance().loadConfig(ScratchCardGeneralConfig.class, Paths.get(HauoliCasino.SCRATCH_PATH + File.separator + "Scratchcard.yml"));
    ScratchCardLang _lang = ConfigManagement.getInstance().loadConfig(ScratchCardLang.class, Paths.get(HauoliCasino.SCRATCH_PATH + File.separator + "Lang.yml"));
    private final ChestTemplate template;

    Button panes = GooeyButton.builder()
            .display(new ItemStack(Blocks.STAINED_GLASS_PANE, 1, _config.paneColor))
            .title(TextUtils.regex(_lang.glassPanes))
            .build();

    Button startGame = GooeyButton.builder()
            .display(ItemUtils.itemStackFromType(_config.gameItem, 1))
            .title(TextUtils.regex(_lang.startGame))
            .lore(TextUtils.regexList(_lang.gameInfo))
            .onClick((buttonAction -> {
                EntityPlayerMP player = buttonAction.getPlayer();
                if(EconomyUtils.hasEnoughMoney(player, _config.cost)) {
                    EconomyUtils.takeMoney(player, _config.cost);
                    fillScratches();
                } else {
                    player.sendMessage(TextUtils.toText(TextUtils.regex(_lang.noMoney)));
                }
            }))
            .build();

    Button notAvailable = GooeyButton.builder()
            .display(ItemUtils.itemStackFromType(_config.unavailableItem, 1))
            .title(TextUtils.regex(_lang.unavailable))
            .build();

    Button blank = GooeyButton.builder()
            .display(ItemUtils.itemStackFromType(_config.blankItem, 1))
            .title(TextUtils.regex(_lang.blank))
            .build();

    Button concealed = GooeyButton.builder()
            .display(ItemUtils.itemStackFromType(_config.concealedItem, 1))
            .title(TextUtils.regex(_lang.concealedCard))
            .onClick((buttonAction -> {
                getTemplate().getSlot(buttonAction.getSlot()).setButton(blank);
            }))
            .build();

    public ScratchCardInventory() {
        this.template = ChestTemplate.builder(5)
                .fill(panes)
                .set(24, startGame)
                .rectangle(1, 1, 3, 3, notAvailable)
                .build();
    }

    private void fillScratches() {
        int slotIndex = 10;
        int rewards = 0;
        ItemStack concealedItem = ItemUtils.itemStackFromType(_config.concealedItem, 1);
        concealedItem.setStackDisplayName(TextUtils.regex(_lang.concealedCard));
        for (int b = 0; b < 3; b++) {
            for (int i = 0; i < 3; i++) {
                int chance = (int) (Math.random() * 100 + 1);
                if (chance <= _config.chance && rewards < _config.maxRewards) {
                    template.set(slotIndex, new ScratchCardButton(concealedItem));
                    slotIndex = slotIndex + 1;
                    rewards = rewards + 1;
                } else {
                    template.set(slotIndex, concealed);
                    slotIndex = slotIndex + 1;
                }
            }
            slotIndex = slotIndex + 6;
        }
    }


    @Override
    public Template getTemplate() {
        return template;
    }

    @Override
    public String getTitle() {
        return TextUtils.regex(_lang.menuTitle);
    }
}
