package org.examples.fightmonsters.工匠;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.examples.fightmonsters.Fightmonsters;


public class NBT {
    private static final NamespacedKey 救急 = new NamespacedKey(Fightmonsters.getInstance(),"救急");

    public static NBT NbtSet = new NBT();
    public void Set救急(ItemStack Item,String Type){
        ItemMeta itemMeta = Item.getItemMeta();
        PersistentDataContainer per = itemMeta.getPersistentDataContainer();
        per.set(救急,PersistentDataType.STRING, Type);
        Item.setItemMeta(itemMeta);
    }
    public String Get救急(ItemStack Item){
        ItemMeta itemMeta = Item.getItemMeta();
        PersistentDataContainer per = itemMeta.getPersistentDataContainer();
        if (per.has(救急)){
            return per.get(救急,PersistentDataType.STRING);
        }
        return "null";
    }



}

