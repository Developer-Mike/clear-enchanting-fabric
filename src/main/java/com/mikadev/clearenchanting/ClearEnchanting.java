package com.mikadev.clearenchanting;

import net.fabricmc.api.ClientModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClearEnchanting implements ClientModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("clear-enchanting");

    @Override
    public void onInitializeClient() {
        LOGGER.info("Clear Enchanting is initializing!");
    }
}