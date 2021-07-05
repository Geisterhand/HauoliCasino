package eu.mccluster.hauolicasino.menu;

import ca.landonjw.gooeylibs2.api.button.Button;
import ca.landonjw.gooeylibs2.api.button.GooeyButton;
import ca.landonjw.gooeylibs2.api.data.UpdateEmitter;
import ca.landonjw.gooeylibs2.api.page.Page;
import ca.landonjw.gooeylibs2.api.template.Template;
import ca.landonjw.gooeylibs2.api.template.types.ChestTemplate;
import ca.landonjw.gooeylibs2.api.template.types.DispenserTemplate;
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

public class FreeScratchCardInventory extends UpdateEmitter<Page> implements Page {

    ScratchCardGeneralConfig _config = HauoliCasino.getScratchConfig();
    ScratchCardLang _lang = HauoliCasino.getScratchLang();
    private final DispenserTemplate template;

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

    public FreeScratchCardInventory() {
        this.template = DispenserTemplate.builder()
                .build();
        fillScratches();
    }

    private void fillScratches() {
        int slotIndex = 0;
        int rewards = 0;
        ItemStack concealedItem = ItemUtils.itemStackFromType(_config.concealedItem, 1);
        concealedItem.setStackDisplayName(TextUtils.regex(_lang.concealedCard));
            for (int i = 0; i < 9; i++) {
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
    }


    @Override
    public Template getTemplate() {
        return template;
    }

    @Override
    public String getTitle() {
        return TextUtils.regex(_lang.freeCard);
    }
}
