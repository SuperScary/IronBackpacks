package gr8pefish.ironbackpacks.item;

import com.google.common.collect.Lists;
import gr8pefish.ironbackpacks.IronBackpacks;
import gr8pefish.ironbackpacks.api.*;
import gr8pefish.ironbackpacks.core.ModObjects;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

import java.util.Comparator;
import java.util.List;

public class ItemBackpack extends Item implements IBackpack {

    public ItemBackpack() {
        setUnlocalizedName(IronBackpacks.MODID + ".backpack");
        setHasSubtypes(true);
        setCreativeTab(IronBackpacks.TAB_IB);
        setMaxStackSize(1);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        BackpackInfo backpackInfo = getBackpackInfo(stack);
        return super.getUnlocalizedName(stack) + "." + backpackInfo.getBackpackType().getIdentifier().toString().replace(":", ".");
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack held = playerIn.getHeldItem(handIn);
        IronBackpacks.LOGGER.info("Item tag: " + held.getTagCompound());
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void getSubItems(Item item, CreativeTabs tab, NonNullList<ItemStack> subItems) {
        List<BackpackType> sortedTypes = Lists.newArrayList(IronBackpacksHelper.getBackpackTypes());
        sortedTypes.sort(Comparator.comparingInt(BackpackType::getTier));

        for (BackpackType backpackType : sortedTypes) {
            if (backpackType.getIdentifier().equals(IronBackpacksHelper.NULL))
                continue;

            if (!backpackType.hasSpecialties()) {
                subItems.add(IronBackpacksHelper.getStack(backpackType, BackpackSpecialty.NONE));
            } else {
                for (BackpackSpecialty specialty : BackpackSpecialty.values()) {
                    if (specialty == BackpackSpecialty.NONE)
                        continue;

                    subItems.add(IronBackpacksHelper.getStack(backpackType, specialty));
                }
            }
        }
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return getBackpackInfo(stack).hasUpgrade(ModObjects.DAMAGE_BAR_UPGADE);
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack) {
        int total = 0;
        int full = 0;

        BackpackInfo backpackInfo = getBackpackInfo(stack);
        for (int i = 0; i < backpackInfo.getStackHandler().getSlots(); i++) {
            ItemStack invStack = backpackInfo.getStackHandler().getStackInSlot(i);
            if (!invStack.isEmpty()) {
                full += invStack.getCount();
                total += invStack.getMaxStackSize();
            } else {
                total += 64;
            }
        }

        return 1.0D - ((double) full / (double) total);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced) {
        BackpackInfo backpackInfo = getBackpackInfo(stack);
        if (backpackInfo.getBackpackType().hasSpecialties())
            tooltip.add(I18n.format("tooltip.ironbackpacks.backpack.emphasis." + backpackInfo.getSpecialty().getName()));
        tooltip.add(I18n.format("tooltip.ironbackpacks.backpack.tier", backpackInfo.getBackpackType().getTier() + 1));
        tooltip.add(I18n.format("tooltip.ironbackpacks.backpack.upgrade.used", backpackInfo.getPointsUsed(), backpackInfo.getMaxPoints()));
        if (!Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) && !backpackInfo.getApplied().isEmpty()) {
         tooltip.add(I18n.format("tooltip.ironbackpacks.shift"));
        } else if (!backpackInfo.getApplied().isEmpty()){
            tooltip.add("");
            tooltip.add(I18n.format("tooltip.ironbackpacks.backpack.upgrade.list", backpackInfo.getPointsUsed(), backpackInfo.getMaxPoints()));
            for (BackpackUpgrade upgrade : backpackInfo.getApplied())
                tooltip.add("  - " + I18n.format("upgrade.ironbackpacks." + upgrade.getIdentifier().getResourcePath() + ".name"));
        }
    }
}