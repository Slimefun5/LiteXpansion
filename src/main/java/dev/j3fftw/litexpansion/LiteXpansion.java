package dev.j3fftw.litexpansion;

import dev.j3fftw.litexpansion.resources.ThoriumResource;
import dev.j3fftw.litexpansion.service.MetricsService;
import dev.j3fftw.litexpansion.ticker.PassiveElectricRemovalTicker;
import dev.j3fftw.litexpansion.utils.Reflections;
import dev.j3fftw.litexpansion.uumatter.UUMatter;
import io.github.thebusybiscuit.slimefun5.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun5.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun5.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun5.api.researches.Research;
<<<<<<< HEAD
import org.bstats.MetricsBase;
import org.bukkit.NamespacedKey;
=======
import io.github.thebusybiscuit.slimefun5.core.guide.wiki.WikiText;
import io.github.thebusybiscuit.slimefun5.core.guide.wiki.WikiTopic;
import io.github.thebusybiscuit.slimefun5.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun5.libraries.xseries.XMaterial;
import org.bstats.bukkit.Metrics;
import io.github.thebusybiscuit.slimefun5.libraries.keys.NamespacedKey;
>>>>>>> origin/experimental
import org.bukkit.plugin.java.JavaPlugin;
import dev.walshy.sfmetrics.MetricsModule;

import javax.annotation.Nonnull;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents the {@link LiteXpansion} class.
 *
 * @author j3fftw
 */
public class LiteXpansion extends JavaPlugin implements SlimefunAddon {

    private static LiteXpansion instance;

    private final MetricsService metricsService = new MetricsService();

    @Override
    public void onEnable() {
        MetricsModule.setup(this, 7111);

        setInstance(this);

        if (!new File(getDataFolder(), "config.yml").exists()) {
            saveDefaultConfig();
        }

<<<<<<< HEAD
        final         metricsService.setup(metrics);
=======
        Metrics metrics = new Metrics(this, 7111);
        metricsService.setup(metrics);
>>>>>>> origin/experimental

        if (getConfig().getBoolean("options.nerf-other-addons", true)) {
            getServer().getScheduler().runTask(this, this::nerfCrap);
        }

        ItemSetup.INSTANCE.init();

        getServer().getPluginManager().registerEvents(new Events(), this);

        UUMatter.INSTANCE.register();

        setupResearches();
        new ThoriumResource().register();

        final PassiveElectricRemovalTicker perTicker = new PassiveElectricRemovalTicker();
        getServer().getScheduler().runTaskTimerAsynchronously(this, perTicker, 20, 20);

        // Contribute this addon's per-language item translations (languages/<lang>/items.yml).
        Slimefun.getItemTranslationService().registerTranslations(this);

        // Register this addon's own in-game wiki page (core does not auto-generate addon wikis).
        registerWiki();
    }

    private void registerWiki() {
        WikiText wiki = Slimefun.getWikiText();

        // Bucket this addon's items by their ItemGroup dynamically - never hardcode item lists.
        Map<ItemGroup, List<String>> byGroup = new LinkedHashMap<>();

        for (SlimefunItem item : Slimefun.getRegistry().getEnabledSlimefunItems()) {
            try {
                if (item.getAddon() != this) {
                    continue;
                }
                ItemGroup group = item.getItemGroup();
                if (group == null) {
                    continue;
                }
                byGroup.computeIfAbsent(group, g -> new ArrayList<>()).add(item.getId());

                // Author this item's own wiki page if we have something to say about it.
                List<String> text = itemText(item.getId());
                if (text != null) {
                    wiki.set(item.getId(), text);
                }
            } catch (Exception | LinkageError ignored) {
                // A broken item should not break wiki registration.
            }
        }

        for (Map.Entry<ItemGroup, List<String>> entry : byGroup.entrySet()) {
            try {
                String groupKey = entry.getKey().getKey().getKey();
                String topicId = "addon_litexpansion_" + groupKey;

                wiki.registerTopic(new WikiTopic(topicId,
                    categoryTitle(groupKey), categoryIcon(groupKey), categoryTagline(groupKey)));
                wiki.setMechanic(topicId, categoryBlurb(groupKey));
                wiki.setTopicItems(topicId, entry.getValue());
            } catch (Exception | LinkageError ignored) {
                // A broken group should not break wiki registration.
            }
        }
    }

    private static String categoryTitle(String groupKey) {
        switch (groupKey) {
            case "litexpansion": return "LiteXpansion";
            default: return "LiteXpansion";
        }
    }

    private static XMaterial categoryIcon(String groupKey) {
        switch (groupKey) {
            case "litexpansion": return XMaterial.REDSTONE;
            default: return XMaterial.REDSTONE;
        }
    }

    private static String categoryTagline(String groupKey) {
        switch (groupKey) {
            case "litexpansion": return "&7Classic IndustrialCraft-style tech";
            default: return "&7Classic IndustrialCraft-style tech";
        }
    }

    private static List<String> categoryBlurb(String groupKey) {
        switch (groupKey) {
            case "litexpansion":
            default: return Arrays.asList(
                "&7A faithful take on classic IndustrialCraft.", "",
                "&7Start by macerating ores into dust for double",
                "&7yields, then run them through the &7Refined",
                "&7Smeltery &7and &7Metal Forge &7multiblocks to make",
                "&fplates&7, &fcasings &7and superalloys like &bMag-Thor",
                "&7and &fIridium&7.", "",
                "&7Power everything with &7Generators&7, tiered &eSolar",
                "&7Panels &7and &7RE-Batteries&7, wiring blocks together",
                "&7with insulated Tin, Copper and Gold cables into",
                "&7MFE and MFSU storage units.", "",
                "&7Feed junk into the &8Recycler &7to make &8Scrap&7,",
                "&7then convert it to &5UU-Matter &7in the &5Mass",
                "&7Fabricator &7- the universal matter you can craft",
                "&7almost anything from.", "",
                "&7Top it off with electric tools, the &2Nano Blade&7,",
                "&7the &9Electric Chestplate &7and a full line of",
                "&7solar helmets. Click an item below for its recipe.");
        }
    }

    private static List<String> itemText(String id) {
        switch (id) {
            // --- Core matter loop ---
            case "SCRAP_MACHINE": return Arrays.asList(
                "&8The Recycler.",
                "&7Feeds in almost any item and has a chance to",
                "&7spit out &8Scrap &7- the raw feedstock for UU-Matter.",
                "&7Runs on energy; the rest is destroyed.");
            case "SCRAP": return Arrays.asList(
                "&8Compacted junk produced by the &8Recycler&7.",
                "&7Worthless on its own, but the &5Mass Fabricator",
                "&7converts it straight into &5UU-Matter &7much faster.");
            case "MASS_FABRICATOR_MACHINE": return Arrays.asList(
                "&5Converts energy - and optionally &8Scrap &7- into",
                "&5UU-Matter&7, the universal building material.",
                "&7Adding Scrap dramatically reduces the energy cost.",
                "&8A hungry, end-game machine.");
            case "UU_MATTER": return Arrays.asList(
                "&5Universal matter fabricated from raw energy.",
                "&7The cornerstone of LiteXpansion: craft it into",
                "&7ores, gems and rare resources via the UU-Matter",
                "&7recipe category in your guide.");
            case "UU_CRAFTER": return Arrays.asList(
                "&7Automates &5UU-Matter &7crafting.",
                "&7Supply UU-Matter and it produces the configured",
                "&7resource without manual grid crafting.");

            // --- Ore processing & smelting machines ---
            case "MACERATOR": return Arrays.asList(
                "&7Grinds ores and metals into &7dust&7, doubling",
                "&7your yield when the dust is smelted back down.",
                "&7The first step of efficient ore processing.");
            case "REFINED_SMELTERY": return Arrays.asList(
                "&7A multiblock smeltery.",
                "&7Smelts dusts and combines metals into alloys",
                "&7such as &7Mixed Metal Ingots &7and &7Refined Iron&7.",
                "&8Build the structure, then click the dispenser.");
            case "METAL_FORGE": return Arrays.asList(
                "&7A multiblock metal press.",
                "&7Forms ingots into &fplates &7and &fitem casings &7-",
                "&7the components behind cables, machines and armor.");
            case "MANUAL_MILL": return Arrays.asList(
                "&7A hand-cranked multiblock mill.",
                "&7Grinds materials without any power, giving you an",
                "&7early-game alternative to the electric Macerator.");
            case "RUBBER_SYNTHESIZER": return Arrays.asList(
                "&6Converts a Bucket of Oil into &7Rubber&7.",
                "&7Rubber insulates bare cables and is used widely",
                "&7in circuits and machine recipes.");
            case "FOOD_SYNTHESIZER": return Arrays.asList(
                "&dKeeps you fed using stored energy.",
                "&7Produces artificial food on demand so you never",
                "&7have to stop working to eat.");
            case "CONVER": return Arrays.asList(
                "&7The Converter.",
                "&7Swaps energy between LiteXpansion and other power",
                "&7systems so your grids can interoperate.");

            // --- Power generation ---
            case "GENERATOR": return Arrays.asList(
                "&7Burns fuel to produce energy.",
                "&7Your first reliable power source and the basis",
                "&7for charging RE-Batteries and running machines.");
            case "ADVANCED_SOLAR_PANEL": return Arrays.asList(
                "&7A solar panel that &9also works at night&7,",
                "&7at a reduced rate.",
                "&7Includes its own internal energy buffer.");
            case "HYBRID_SOLAR_PANEL": return Arrays.asList(
                "&bA dimension-aware solar panel.",
                "&7Boosted output in the Nether by day and the End",
                "&7by night, on top of normal day/night generation.");
            case "ULTIMATE_SOLAR_PANEL": return Arrays.asList(
                "&5The strongest LiteXpansion solar panel.",
                "&7Generates large amounts of energy day and night",
                "&7with a sizeable built-in buffer.");

            // --- Energy storage ---
            case "RE_BATTERY": return Arrays.asList(
                "&7A rechargeable battery.",
                "&7Stores energy and powers portable electric tools",
                "&7and armor. The basic building block of power.");
            case "MULTI_FUNCTIONAL_ELECTRIC_STORAGE_UNIT": return Arrays.asList(
                "&7The MFE.",
                "&7A mid-tier energy capacitor storing a large",
                "&7buffer for your machine network.");
            case "MULTI_FUNCTIONAL_STORAGE_UNIT": return Arrays.asList(
                "&7The MFSU.",
                "&7A high-tier capacitor with a huge energy buffer",
                "&7for powering demanding end-game machines.");
            case "LAPOTRON_CRYSTAL": return Arrays.asList(
                "&bA portable high-capacity power cell.",
                "&7Charges in a capacitor, then carries energy to",
                "&7top up your tools and armor in the field.");

            // --- Tools ---
            case "MINING_DRILL": return Arrays.asList(
                "&7An electric drill.",
                "&7Instantly breaks stone and its variants while",
                "&7powered. Right-click to mine more slowly.");
            case "DIAMOND_DRILL": return Arrays.asList(
                "&bAn upgraded electric drill.",
                "&7Instantly breaks obsidian, stone and stone",
                "&7variants. Right-click to mine slowly.");
            case "GLASS_CUTTER": return Arrays.asList(
                "&bAn electric glass tool.",
                "&7Left-click cuts glass quickly, right-click cuts",
                "&7slowly. Runs off its internal energy buffer.");
            case "TREETAP": return Arrays.asList(
                "&7Used to harvest &7Rubber &7from rubber sources",
                "&7by hand, without an electric synthesizer.");
            case "CARGO_CONFIGURATOR": return Arrays.asList(
                "&7Copies and pastes cargo node configurations.",
                "&7Right-click a node to copy, left-click to apply,",
                "&7shift-right-click to clear the stored config.");

            // --- Weapons & armor ---
            case "NANO_BLADE": return Arrays.asList(
                "&2An advanced energy sword.",
                "&7Right-click to toggle it on; while powered and",
                "&7active it cuts through tissue with ease.",
                "&8Consumes energy with each strike.");
            case "ELECTRIC_CHESTPLATE": return Arrays.asList(
                "&9A powered chestplate.",
                "&7While charged, it negates all incoming damage,",
                "&7draining energy to absorb each hit.");
            case "ADVANCED_SOLAR_HELMET": case "HYBRID_SOLAR_HELMET": case "ULTIMATE_SOLAR_HELMET":
            case "CARBONADO_SOLAR_HELMET": case "ENERGIZED_SOLAR_HELMET": case "ADVANCEDLX_SOLAR_HELMET":
                return Arrays.asList(
                    "&eA solar helmet.",
                    "&7Worn on the head, it passively generates energy",
                    "&7in daylight and charges your other equipment.",
                    "&7Higher tiers produce and store far more.");

            // --- Key materials & superalloys ---
            case "THORIUM": return Arrays.asList(
                "&8A radioactive element.",
                "&cRequires a Hazmat Suit to handle safely.",
                "&7Refined into &7Mag-Thor &7and used in advanced",
                "&7plating and machinery.");
            case "MAG_THOR": return Arrays.asList(
                "&bAn extremely durable superalloy.",
                "&7Forged from Thorium and used only in the most",
                "&7advanced machines and components.");
            case "IRIDIUM": return Arrays.asList(
                "&fA rare, dense metal.",
                "&7Pressed into Iridium Plates for the toughest",
                "&7armor in the addon. Often made from UU-Matter.");
            case "ADVANCED_ALLOY": return Arrays.asList(
                "&7A reinforced alloy plate.",
                "&7A key component in advanced machines, reinforced",
                "&7blocks and high-tier recipes.");
            case "MIXED_METAL_INGOT": return Arrays.asList(
                "&7Layered plates fused in the &7Refined Smeltery&7.",
                "&7The precursor to &7Advanced Alloy&7.");
            case "REFINED_IRON": return Arrays.asList(
                "&7Purified iron with the impurities smelted out.",
                "&7Used throughout machine and tool recipes.");
            case "ELECTRONIC_CIRCUIT": return Arrays.asList(
                "&7A basic circuit board.",
                "&7The control component inside most machines and",
                "&7electric items.");
            case "ADVANCED_CIRCUIT": return Arrays.asList(
                "&7An upgraded circuit board.",
                "&7Required by higher-tier machines and gear that",
                "&7the basic circuit can't drive.");

            default: return null;
        }
    }

    @Override
    public void onDisable() {
        setInstance(null);
    }

    private void nerfCrap() {
        // Vanilla SF
        final SlimefunItem energizedPanel = SlimefunItem.getById("SOLAR_GENERATOR_4");
        if (energizedPanel != null) {
            Reflections.setField(energizedPanel, "dayEnergy", 64);
            Reflections.setField(energizedPanel, "nightEnergy", 32);
        }

        // InfinityExpansion - Halved all values and made infinite panel + infinity reactor much less
        Reflections.setField(SlimefunItem.getById("ADVANCED_PANEL"), "generation", 75);
        Reflections.setField(SlimefunItem.getById("CELESTIAL_PANEL"), "generation", 250);
        Reflections.setField(SlimefunItem.getById("VOID_PANEL"), "generation", 1200);
        Reflections.setField(SlimefunItem.getById("INFINITE_PANEL"), "generation", 20_000);
        Reflections.setField(SlimefunItem.getById("INFINITY_REACTOR"), "gen", 50_000);

        // SlimefunWarfare - Halved all values
        Reflections.setField(SlimefunItem.getById("ELEMENTAL_REACTOR"), "energyProducedPerTick", 8_192);

        // Galactifun
        Reflections.setField(SlimefunItem.getById("FUSION_REACTOR"), "energyProducedPerTick", 8_192);

        // SupremeExpansion - just no...
        Reflections.setField(SlimefunItem.getById("SUPREME_SUPREME_GENERATOR"), "energy", 20_000);
        Reflections.setField(SlimefunItem.getById("SUPREME_THORNIUM_GENERATOR"), "energy", 10_000);
        Reflections.setField(SlimefunItem.getById("SUPREME_LUMIUM_GENERATOR"), "energy", 5_000);
        Reflections.setField(SlimefunItem.getById("SUPREME_BASIC_LUMIUM_GENERATOR"), "energy", 500);
        Reflections.setField(SlimefunItem.getById("SUPREME_LUX_GENERATOR"), "energy", 2_500);
        Reflections.setField(SlimefunItem.getById("SUPREME_BASIC_LUX_GENERATOR"), "energy", 250);
        Reflections.setField(SlimefunItem.getById("SUPREME_AQUA_GENERATOR"), "energy", 2_500);
        Reflections.setField(SlimefunItem.getById("SUPREME_BASIC_AQUA_GENERATOR"), "energy", 250);
        Reflections.setField(SlimefunItem.getById("SUPREME_VENUS_GENERATOR"), "energy", 2_500);
        Reflections.setField(SlimefunItem.getById("SUPREME_BASIC_VENUS_GENERATOR"), "energy", 250);
        Reflections.setField(SlimefunItem.getById("SUPREME_IGNIS_GENERATOR"), "energy", 2_500);
        Reflections.setField(SlimefunItem.getById("SUPREME_BASIC_IGNIS_GENERATOR"), "energy", 250);
    }

    private void setupResearches() {
        new Research(new NamespacedKey("litexpansion", "sanitizing_foots"),
            696969, "Sanitizing  foots since 2k10", 45)
            .addItems(Items.FOOD_SYNTHESIZER.item())
            .register();

        new Research(new NamespacedKey("litexpansion", "superalloys"),
            696970, "Superalloys", 35)
            .addItems(Items.THORIUM.item(), Items.MAG_THOR.item(), Items.IRIDIUM.item(), Items.ADVANCED_ALLOY.item(), Items.MIXED_METAL_INGOT.item(),
                Items.REFINED_IRON.item())
            .register();

        new Research(new NamespacedKey("litexpansion", "super_hot_fire"),
            696971, "Super Hot Fire", 31)
            .addItems(Items.NANO_BLADE.item(), Items.ELECTRIC_CHESTPLATE.item())
            .register();

        new Research(new NamespacedKey("litexpansion", "machinereee"),
            696972, "Machinereeeeee", 30)
            .addItems(Items.METAL_FORGE.item(), Items.REFINED_SMELTERY.item(), Items.RUBBER_SYNTHESIZER_MACHINE.item(), Items.MANUAL_MILL.item(),
                Items.GENERATOR.item())
            .register();

        new Research(new NamespacedKey("litexpansion", "the_better_panel"),
            696973, "These are the better panels", 45)
            .addItems(Items.ADVANCED_SOLAR_PANEL.item(), Items.ULTIMATE_SOLAR_PANEL.item(), Items.HYBRID_SOLAR_PANEL.item())
            .register();

        new Research(new NamespacedKey("litexpansion", "does_this_even_matter"),
            696974, "Does this even matter", 150)
            .addItems(Items.UU_MATTER.item(), Items.SCRAP.item(), Items.MASS_FABRICATOR_MACHINE.item(), Items.RECYCLER.item())
            .register();

        new Research(new NamespacedKey("litexpansion", "what_a_configuration"),
            696975, "What a configuration", 39)
            .addItems(Items.CARGO_CONFIGURATOR.item())
            .register();

        new Research(new NamespacedKey("litexpansion", "platings"),
            696976, "Platings", 40)
            .addItems(Items.IRIDIUM_PLATE.item(), Items.COPPER_PLATE.item(), Items.TIN_PLATE.item(), Items.DIAMOND_PLATE.item(), Items.IRON_PLATE.item(),
                Items.GOLD_PLATE.item(), Items.THORIUM_PLATE.item())
            .register();

        new Research(new NamespacedKey("litexpansion", "rubber"),
            696977, "Rubber", 25)
            .addItems(Items.RUBBER.item())
            .register();

        new Research(new NamespacedKey("litexpansion", "circuits"),
            696978, "Circuits", 25)
            .addItems(Items.ELECTRONIC_CIRCUIT.item(), Items.ADVANCED_CIRCUIT.item())
            .register();

        new Research(new NamespacedKey("litexpansion", "reinforcement_is_coming"),
            696979, "Reinforcement is coming", 15)
            .addItems(Items.REINFORCED_DOOR.item(), Items.REINFORCED_GLASS.item(), Items.REINFORCED_STONE.item())
            .register();

        new Research(new NamespacedKey("litexpansion", "only_glass"),
            696980, "Only glass", 40)
            .addItems(Items.GLASS_CUTTER.item())
            .register();

        new Research(new NamespacedKey("litexpansion", "machine_blocks"),
            696981, "Machine Blocks", 35)
            .addItems(Items.MACHINE_BLOCK.item(), Items.ADVANCED_MACHINE_BLOCK.item())
            .register();

        new Research(new NamespacedKey("litexpansion", "coal_mesh"),
            696982, "Coal mesh", 30)
            .addItems(Items.COAL_DUST.item(), Items.RAW_CARBON_MESH.item(), Items.RAW_CARBON_FIBRE.item(), Items.CARBON_PLATE.item())
            .register();

        new Research(new NamespacedKey("litexpansion", "what_are_these_cables"),
            696983, "What are these cables", 25)
            .addItems(Items.UNINSULATED_COPPER_CABLE.item(), Items.COPPER_CABLE.item(),
                Items.UNINSULATED_COPPER_CABLE.item(), Items.TIN_CABLE.item())
            .register();

        new Research(new NamespacedKey("litexpansion", "triple_a"),
            696984, "Triple a", 20)
            .addItems(Items.RE_BATTERY.item())
            .register();

        new Research(new NamespacedKey("litexpansion", "casing"),
            696985, "S 340", 20)
            .addItems(Items.TIN_ITEM_CASING.item(), Items.COPPER_ITEM_CASING.item())
            .register();

        new Research(new NamespacedKey("litexpansion", "solar_helmets"),
            696986, "More solar helmets", 30)
            .addItems(Items.HYBRID_SOLAR_HELMET.item(), Items.ADVANCED_SOLAR_HELMET.item(), Items.ADVANCEDLX_SOLAR_HELMET.item(),
                Items.CARBONADO_SOLAR_HELMET.item(), Items.ENERGIZED_SOLAR_HELMET.item(), Items.ULTIMATE_SOLAR_HELMET.item())
            .register();
    }

    @Nonnull
    public JavaPlugin getJavaPlugin() {
        return this;
    }

    public String getBugTrackerURL() {
        return "https://github.com/Slimefun5/LiteXpansion/issues";
    }

    public static LiteXpansion getInstance() {
        return instance;
    }

    private static void setInstance(LiteXpansion ins) {
        instance = ins;
    }
}

