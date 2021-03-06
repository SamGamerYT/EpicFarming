package com.songoda.epicfarming.utils;

import com.songoda.arconix.plugin.Arconix;
import com.songoda.epicfarming.EpicFarming;
import com.songoda.epicfarming.farming.Level;
import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Methods {

    public static ItemStack getGlass() {
        try {
            EpicFarming plugin = EpicFarming.pl();
            return Arconix.pl().getApi().getGUI().getGlass(plugin.getConfig().getBoolean("settings.Rainbow-Glass"), plugin.getConfig().getInt("Interfaces.Glass Type 1"));
        } catch (Exception ex) {
            Debugger.runReport(ex);
        }
        return null;
    }

    public static ItemStack getBackgroundGlass(boolean type) {
        try {
            EpicFarming plugin = EpicFarming.pl();
            if (type)
                return Arconix.pl().getApi().getGUI().getGlass(false, plugin.getConfig().getInt("Interfaces.Glass Type 2"));
            else
                return Arconix.pl().getApi().getGUI().getGlass(false, plugin.getConfig().getInt("Interfaces.Glass Type 3"));
        } catch (Exception ex) {
            Debugger.runReport(ex);
        }
        return null;
    }

    public static int getLevelFromItem(ItemStack item) {
        try {
            if (!item.hasItemMeta() || !item.getItemMeta().hasDisplayName()) return 0;
            if (item.getItemMeta().getDisplayName().contains(":")) {
                return NumberUtils.toInt(item.getItemMeta().getDisplayName().replace("\u00A7", "").split(":")[0], 0);
            }
        } catch (Exception ex) {
            Debugger.runReport(ex);
        }
        return 0;
    }

    public static String formatName(int level, boolean full) {
        try {
            String name = EpicFarming.getInstance().getLocale().getMessage("general.nametag.farm", level);

            String info = "";
            if (full) {
                info += Arconix.pl().getApi().format().convertToInvisibleString(level + ":");
            }

            return info + Arconix.pl().getApi().format().formatText(name);
        } catch (Exception ex) {
            Debugger.runReport(ex);
        }
        return null;
    }

    public static ItemStack makeFarmItem(Level level) {
        ItemStack item = new ItemStack(Material.valueOf(EpicFarming.getInstance().getConfig().getString("Main.Farm Block Material")), 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Arconix.pl().getApi().format().formatText(Methods.formatName(level.getLevel(), true)));
        item.setItemMeta(meta);
        return item;
    }
}