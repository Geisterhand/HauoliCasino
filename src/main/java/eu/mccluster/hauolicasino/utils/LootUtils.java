package eu.mccluster.hauolicasino.utils;

import eu.mccluster.hauolicasino.HauoliCasino;
import eu.mccluster.hauolicasino.config.LootTableStart;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class LootUtils {

    public static ItemStack itemStackFromType(String itemName, int quantity) {
        ItemStack itemStack = GameRegistry.makeItemStack(itemName, 0, quantity, null);
        return itemStack;
    }

    public static void genLoot(EntityPlayerMP playerMP, int amount) {

        int raritySum;
        LootTableStart _loottable = HauoliCasino.getLoot();

        if(HauoliCasino.getLoot().toggleExtraLoot && amount == 5) {
            amount = amount + HauoliCasino.getLoot().extraLoot;
        }

            raritySum = _loottable.loottable.lootData.stream().mapToInt(lootTableData -> lootTableData.lootRarity).sum();
            for (int i = 0; i < amount; i++) {
                int pickedRarity = (int) (raritySum * Math.random());
                int listEntry = -1;

                for (int b = 0; b <= pickedRarity; ) {
                    listEntry = listEntry + 1;
                    b = _loottable.loottable.lootData.get(listEntry).lootRarity + b;

                }

                if(!_loottable.loottable.lootData.get(listEntry).loot.startsWith("command>")) {
                    ItemStack rewardItem = itemStackFromType(_loottable.loottable.lootData.get(listEntry).loot, _loottable.loottable.lootData.get(listEntry).lootAmount);
                    playerMP.inventory.addItemStackToInventory(rewardItem);
                } else {
                    String command = _loottable.loottable.lootData.get(listEntry).loot.replaceAll("command>", "");
                    MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
                    server.getCommandManager().executeCommand(server, Placeholder.parsePlayer(command, playerMP));
                }
            }
        }
    }
