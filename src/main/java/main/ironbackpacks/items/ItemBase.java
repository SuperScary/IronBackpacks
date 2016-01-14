package main.ironbackpacks.items;

import main.ironbackpacks.IronBackpacks;
import main.ironbackpacks.api.Constants;
import net.minecraft.item.Item;

/**
 * A very basic item class that every item on this mod extends
 */
public class ItemBase extends Item {

	public ItemBase(String unlocName, String textureName) { //TODO: remove texture name
		super();
		setUnlocalizedName(Constants.ID + ":" + unlocName);
		setCreativeTab(IronBackpacks.creativeTab);
	}

}
