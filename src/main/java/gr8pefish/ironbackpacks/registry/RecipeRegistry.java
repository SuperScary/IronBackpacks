package gr8pefish.ironbackpacks.registry;

import java.util.ArrayList;
import java.util.List;

import gr8pefish.ironbackpacks.api.Constants;
import gr8pefish.ironbackpacks.api.items.backpacks.interfaces.IBackpack;
import gr8pefish.ironbackpacks.api.items.backpacks.interfaces.ITieredBackpack;
import gr8pefish.ironbackpacks.api.items.backpacks.interfaces.IUpgradableBackpack;
import gr8pefish.ironbackpacks.api.recipes.IAddUpgradeRecipe;
import gr8pefish.ironbackpacks.api.recipes.IIncreaseBackpackTierRecipe;
import gr8pefish.ironbackpacks.api.recipes.IRemoveUpgradeRecipe;
import gr8pefish.ironbackpacks.api.register.IAllRecipesRegistry;
import gr8pefish.ironbackpacks.api.register.ItemIBackpackRegistry;
import gr8pefish.ironbackpacks.api.register.ItemIUpgradeRegistry;
import gr8pefish.ironbackpacks.config.ConfigHandler;
import gr8pefish.ironbackpacks.crafting.BackpackAddUpgradeRecipe;
import gr8pefish.ironbackpacks.crafting.BackpackIncreaseTierRecipe;
import gr8pefish.ironbackpacks.crafting.BackpackRemoveUpgradeRecipe;
import gr8pefish.ironbackpacks.items.backpacks.ItemBackpack;
import gr8pefish.ironbackpacks.libs.recipes.ItemBackpackRecipes;
import gr8pefish.ironbackpacks.libs.recipes.ItemCraftingRecipes;
import gr8pefish.ironbackpacks.libs.recipes.ItemUpgradeRecipes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;

/**
 * Register all the recipes here.
 */
public class RecipeRegistry {
	
	public static final List<IAddUpgradeRecipe> UPGRADE_ADD = new ArrayList<>();
	public static final List<IRemoveUpgradeRecipe> UPGRADE_REMOVE = new ArrayList<>();
	public static final List<IIncreaseBackpackTierRecipe> INCREASE = new ArrayList<>();
	
	/**
	 * Main method that registers all the recipes
	 */
	public static void registerAllRecipes(IForgeRegistry<IRecipe> reg) {

        //Basic Item Recipes
        ItemCraftingRecipes.registerItemCraftingRecipes(); //register the recipes to get the recipes items
        ItemUpgradeRecipes.registerItemUpgradeRecipes(); //register the recipes to get the the upgrades as items
		ItemBackpackRecipes.registerItemBackpackRecipes(); //register all the recipes to get the backpacks directly

        //Register the recipes themselves
        registerBackpackTierRecipes(reg); //register the recipes to upgrade a backpack to the next tier
		registerBackpackUpgradeRemovalRecipes(reg); //register the recipes to remove upgrades from backpacks
        registerBackpackUpgradeAdditionRecipes(reg); //register the recipes to add upgrades from backpacks


	}

    /**
     * Sets all the non tiered backpack recipes.
     * Not in items initialization in case one items uses another items from this mod, as that items may not be initialized yet (very likely).
     */
    public static void setAllNonTierRecipes(){
        setItemBackpackRecipes();
        setUpgradeRecipes();
    }

	//===========================================================================Set Recipes==================================================================

    private static void setItemBackpackRecipes(){
        ItemRegistry.basicBackpack.setItemRecipe(ItemBackpackRecipes.basicBackpackRecipe);
    }

    private static void setUpgradeRecipes(){
        if (!ConfigHandler.additionalUpgradePointsRecipeDisabled)
            ItemRegistry.additionalUpgradePointsUpgrade.setItemRecipe(ItemUpgradeRecipes.additionalUpgradePointsRecipe);
        if (!ConfigHandler.buttonUpgradeRecipeDisabled)
            ItemRegistry.buttonUpgrade.setItemRecipe(ItemUpgradeRecipes.buttonUpgradeRecipe);
        if (!ConfigHandler.damageBarUpgradeRecipeDisabled)
            ItemRegistry.damageBarUpgrade.setItemRecipe(ItemUpgradeRecipes.damageBarUpgradeRecipe);
        if (!ConfigHandler.depthUpgradeRecipeDisabled)
            ItemRegistry.depthUpgrade.setItemRecipe(ItemUpgradeRecipes.depthUpgradeRecipe);
        if (!ConfigHandler.eternityUpgradeRecipeDisabled)
            ItemRegistry.eternityUpgrade.setItemRecipe(ItemUpgradeRecipes.eternityUpgradeRecipe);
        if (ConfigHandler.renamingUpgradeRequired && !ConfigHandler.renamingUpgradeRecipeDisabled)
            ItemRegistry.renamingUpgrade.setItemRecipe(ItemUpgradeRecipes.renamingUpgradeRecipe);
        if (!ConfigHandler.nestingUpgradeRecipeDisabled)
            ItemRegistry.nestingUpgrade.setItemRecipe(ItemUpgradeRecipes.nestingUpgradeRecipe);
        if (!ConfigHandler.nestingUpgradeRecipeDisabled)
            ItemRegistry.nestingAdvancedUpgrade.setItemRecipe(ItemUpgradeRecipes.nestingAdvancedUpgradeRecipe);
        if (!ConfigHandler.quickDepositUpgradeRecipeDisabled)
            ItemRegistry.quickDepositUpgrade.setItemRecipe(ItemUpgradeRecipes.quickDepositUpgradeRecipe);
        if (!ConfigHandler.quickDepositPreciseUpgradeRecipeDisabled)
            ItemRegistry.quickDepositPreciseUpgrade.setItemRecipe(ItemUpgradeRecipes.quickDepositPreciseUpgradeRecipe);
        if (!ConfigHandler.craftingUpgradeRecipeDisabled)
            ItemRegistry.craftingUpgrade.setItemRecipe(ItemUpgradeRecipes.craftingUpgradeRecipe);
        if (!ConfigHandler.craftingSmallUpgradeRecipeDisabled)
            ItemRegistry.craftingSmallUpgrade.setItemRecipe(ItemUpgradeRecipes.craftingSmallUpgradeRecipe);
        if (!ConfigHandler.craftingTinyUpgradeRecipeDisabled)
            ItemRegistry.craftingTinyUpgrade.setItemRecipe(ItemUpgradeRecipes.craftingTinyUpgradeRecipe);
        if (!ConfigHandler.filterBasicUpgradeRecipeDisabled)
            ItemRegistry.filterBasicUpgrade.setItemRecipe(ItemUpgradeRecipes.filterBasicUpgradeRecipe);
        if (!ConfigHandler.filterFuzzyUpgradeRecipeDisabled)
            ItemRegistry.filterFuzzyUpgrade.setItemRecipe(ItemUpgradeRecipes.filterFuzzyUpgradeRecipe);
        if (!ConfigHandler.filterOreDictUpgradeRecipeDisabled)
            ItemRegistry.filterOreDictUpgrade.setItemRecipe(ItemUpgradeRecipes.filterOreDictUpgradeRecipe);
        if (!ConfigHandler.filterModSpecificUpgradeRecipeDisabled)
            ItemRegistry.filterModSpecificUpgrade.setItemRecipe(ItemUpgradeRecipes.filterModSpecificUpgradeRecipe);
        if (!ConfigHandler.filterAdvancedUpgradeRecipeDisabled)
            ItemRegistry.filterAdvancedUpgrade.setItemRecipe(ItemUpgradeRecipes.filterAdvancedUpgradeRecipe);
        if (!ConfigHandler.filterMiningUpgradeRecipeDisabled)
            ItemRegistry.filterMiningUpgrade.setItemRecipe(ItemUpgradeRecipes.filterMiningUpgradeRecipe);
        if (!ConfigHandler.filterVoidUpgradeRecipeDisabled)
            ItemRegistry.filterVoidUpgrade.setItemRecipe(ItemUpgradeRecipes.filterVoidUpgradeRecipe);
        if (!ConfigHandler.restockingUpgradeRecipeDisabled)
            ItemRegistry.restockingUpgrade.setItemRecipe(ItemUpgradeRecipes.restockingUpgradeRecipe);
    }

    //=================================================================================Helper Registers==========================================================

    /**
     * Register the upgrade removal recipes for every backpack (if it is an IUpgradableBackpack)
     */
    private static void registerBackpackUpgradeRemovalRecipes(IForgeRegistry<IRecipe> reg){
        for (int i = 0; i < ItemIBackpackRegistry.getSize(); i++){
            IBackpack backpack = ItemIBackpackRegistry.getBackpackAtIndex(i);
            if (backpack instanceof IUpgradableBackpack) {
                BackpackRemoveUpgradeRecipe recipe = new BackpackRemoveUpgradeRecipe(new ItemStack((ItemBackpack)backpack), new ItemStack((ItemBackpack)backpack)); //Hardcoded to ItemBackpack
                if(recipe.getRegistryName() == null)
                	recipe.setRegistryName(new ResourceLocation(Constants.MODID, "recipe"+Constants.j++));
                reg.register(recipe);
                IAllRecipesRegistry.registerUpgradeRemovalRecipe(recipe);
            }
        }
    }

    /**
     * Register the recipe for the addition of upgrades to an IUpgradeableBackpack.
     * If the backpack is also an ITieredBackpack, then the tier of the upgrade must be lower than or equal to the backpack to allow it to be applied.
     */
    private static void registerBackpackUpgradeAdditionRecipes(IForgeRegistry<IRecipe> reg) {
        ArrayList<ItemStack> upgrades = new ArrayList<>();
        for (int i = 0; i < ItemIUpgradeRegistry.getTotalSize(); i++)
            upgrades.add(new ItemStack(ItemRegistry.upgradeItem, 1, i));

        for (int i = 0; i < ItemIBackpackRegistry.getSize(); i++) {
            IBackpack backpack = ItemIBackpackRegistry.getBackpackAtIndex(i);
            if (backpack instanceof IUpgradableBackpack) {
                for (ItemStack upgrade : upgrades) {
                    int upgradeTier = ItemIUpgradeRegistry.getItemUpgrade(upgrade).getTier(upgrade);
                    if (backpack instanceof ITieredBackpack) {
                        int backpackTier = ((ITieredBackpack) backpack).getTier(null);
                        if (upgradeTier <= backpackTier) {
                            BackpackAddUpgradeRecipe recipe = new BackpackAddUpgradeRecipe(new ItemStack((ItemBackpack) backpack), upgrade, new ItemStack((ItemBackpack) backpack)); //Hardcoded to ItemBackpack
                            if(recipe.getRegistryName() == null)
                            	recipe.setRegistryName(new ResourceLocation(Constants.MODID, "recipe"+Constants.j++));
                            reg.register(recipe);
                            IAllRecipesRegistry.registerUpgradeAdditionRecipe(recipe);
                        }
                    } else {
                        //TODO: remove casting for other backpacks (and in above for non-tiered ones)
                        BackpackAddUpgradeRecipe recipe = new BackpackAddUpgradeRecipe(new ItemStack((ItemBackpack) backpack), upgrade, new ItemStack((ItemBackpack) backpack)); //Hardcoded to ItemBackpack
                        if(recipe.getRegistryName() == null)
                        	recipe.setRegistryName(new ResourceLocation(Constants.MODID, "recipe"+Constants.j++));
                        reg.register(recipe);
                        IAllRecipesRegistry.registerUpgradeAdditionRecipe(recipe);
                    }
                }
            }
        }
    }

    /**
     * Register the recipe for the backpack to increase a tier.
     */
    public static void registerBackpackTierRecipes(IForgeRegistry<IRecipe> reg){
        for (int i = 0; i < ItemIBackpackRegistry.getSize(); i++){
            IBackpack backpack = ItemIBackpackRegistry.getBackpackAtIndex(i);
            if (backpack instanceof ITieredBackpack) {
                ITieredBackpack newPack = (ITieredBackpack) backpack;
                List<Object[]> recipes = newPack.getTierRecipes(null);
                if (recipes == null) break; //if you have no recipe to upgrade, you can't register that
                List<ITieredBackpack> upgradedPacks = newPack.getBackpacksAbove(null); //unused items stack parameter
                if (!recipes.isEmpty() && upgradedPacks != null && upgradedPacks.size() == recipes.size()) {
                    for (int j = 0; j < recipes.size(); j++) {
                        BackpackIncreaseTierRecipe tierRecipe = new BackpackIncreaseTierRecipe(new ItemStack((ItemBackpack)upgradedPacks.get(j)), recipes.get(j)); //hardcoded to ItemBackpack
                        if(tierRecipe.getRegistryName() == null)
                        	tierRecipe.setRegistryName(new ResourceLocation(Constants.MODID, "recipe"+Constants.j++));
                        reg.register(tierRecipe);
                        IAllRecipesRegistry.registerTierIncreaseRecipe(tierRecipe);
                    }
                }
            }
        }
    }
}
