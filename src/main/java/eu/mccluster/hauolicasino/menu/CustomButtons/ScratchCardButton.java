package eu.mccluster.hauolicasino.menu.CustomButtons;

import ca.landonjw.gooeylibs2.api.button.ButtonAction;
import ca.landonjw.gooeylibs2.api.button.ButtonBase;
import com.pixelmonmod.pixelmon.config.PixelmonItems;
import eu.mccluster.hauolicasino.HauoliCasino;
import eu.mccluster.hauolicasino.config.scratchcard.ScratchCardStart;
import eu.mccluster.hauolicasino.utils.ItemUtils;
import eu.mccluster.hauolicasino.utils.Placeholder;
import eu.mccluster.hauolicasino.utils.TextUtils;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.FMLCommonHandler;

import javax.annotation.Nonnull;
import java.awt.*;

public class ScratchCardButton extends ButtonBase {

    ScratchCardStart _loot = HauoliCasino.getScratchLoot();

    private boolean active = true;

    public ScratchCardButton(@Nonnull ItemStack concealead) {
        super(concealead);
    }

    @Override
    public void onClick(@Nonnull ButtonAction action) {
        EntityPlayerMP player = action.getPlayer();
        if(active) {
            int raritySum = _loot.loottable.lootData.stream().mapToInt(lootTableData -> lootTableData.lootRarity).sum();
                int pickedRarity = (int) (raritySum * Math.random());
                int listEntry = -1;
                for (int b = 0; b <= pickedRarity; ) {
                    listEntry = listEntry + 1;
                    b = _loot.loottable.lootData.get(listEntry).lootRarity + b;
                }if(!_loot.loottable.lootData.get(listEntry).displayItem.startsWith("sprite>")) {
                    ItemStack rewardItem = ItemUtils.itemStackFromType(_loot.loottable.lootData.get(listEntry).displayItem, 1);
                    rewardItem.setStackDisplayName(TextUtils.regex(_loot.loottable.lootData.get(listEntry).rewardTitle));
                setDisplay(rewardItem);
            } else {
                String indexNumber = _loot.loottable.lootData.get(listEntry).displayItem.replaceAll("sprite>", "");
                ItemStack spriteItem = new ItemStack(PixelmonItems.itemPixelmonSprite, 1);
                NBTTagCompound tagCompound = new NBTTagCompound();
                spriteItem.setTagCompound(tagCompound);
                tagCompound.setShort("ndex", Short.parseShort(indexNumber));
                spriteItem.setStackDisplayName(TextUtils.regex(_loot.loottable.lootData.get(listEntry).rewardTitle));
                setDisplay(spriteItem);
            }

            if(!_loot.loottable.lootData.get(listEntry).loot.startsWith("command>")) {
                ItemStack rewardItem = ItemUtils.itemStackFromType(_loot.loottable.lootData.get(listEntry).loot, _loot.loottable.lootData.get(listEntry).lootAmount);
                player.inventory.addItemStackToInventory(rewardItem);
            } else {
                String command = _loot.loottable.lootData.get(listEntry).loot.replaceAll("command>", "");
                MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
                server.getCommandManager().executeCommand(server, Placeholder.parsePlayer(command, player));
            }
                active = false;
            }
        }
    }

