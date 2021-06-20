package eu.mccluster.hauolilottery.utils;

import eu.mccluster.hauolilottery.HauoliLottery;
import eu.mccluster.hauolilottery.config.LootTableStart;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;
import java.util.List;

public class LootUtils {

    public static ItemStack itemStackFromType(String itemName, int quantity) {
        ItemStack itemStack = GameRegistry.makeItemStack(itemName, 0, quantity, null);
        return itemStack;
    }

    public static void genLoot(EntityPlayerMP playerMP,int amount) {

        int raritySum;
        List<ItemStack> outList = new ArrayList<>();;
        LootTableStart _loottable = HauoliLottery.getLoot();

            raritySum = _loottable.loottable.lootData.stream().mapToInt(lootTableData -> lootTableData.lootRarity).sum();
            for (int i = 0; i < amount; i++) {
                int pickedRarity = (int) (raritySum * Math.random());
                int listEntry = -1;

                for (int b = 0; b <= pickedRarity; ) {
                    listEntry = listEntry + 1;
                    b = _loottable.loottable.lootData.get(listEntry).lootRarity + b;

                }
                ItemStack rewardItem = itemStackFromType(_loottable.loottable.lootData.get(listEntry).loot, _loottable.loottable.lootData.get(listEntry).lootAmount);
                outList.add(rewardItem);
            }
        HauoliLottery.getPlayerLoot().put(playerMP.getUniqueID(), outList);
        }
    }

