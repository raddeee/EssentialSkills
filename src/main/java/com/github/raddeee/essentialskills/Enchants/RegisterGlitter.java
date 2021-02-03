package com.github.raddeee.essentialskills.Enchants;

import org.bukkit.enchantments.Enchantment;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Collectors;

public class RegisterGlitter {

    public static final Enchantment GLITTER = new Glitter("glitter", null, 0);

    public static void register(){
        boolean registered = Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(GLITTER);

        if(!registered){
            registerEnchant(GLITTER);
        }
    }

    public static void registerEnchant(Enchantment enchantment){
        try{
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);
            Enchantment.registerEnchantment(enchantment);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
