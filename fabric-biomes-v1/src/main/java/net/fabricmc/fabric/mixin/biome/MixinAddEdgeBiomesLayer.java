/*
 * Copyright (c) 2016, 2017, 2018, 2019 FabricMC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.fabricmc.fabric.mixin.biome;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.layer.AddEdgeBiomesLayer;
import net.minecraft.world.biome.layer.LayerRandomnessSource;

import net.fabricmc.fabric.api.biomes.v1.OverworldBiomes;
import net.fabricmc.fabric.impl.biome.InternalBiomeData;
import net.fabricmc.fabric.impl.biome.InternalBiomeUtils;

/**
 * Adds edges and shores specified in {@link OverworldBiomes#addEdgeBiome(Biome, Biome, double)} and {@link OverworldBiomes#addShoreBiome(Biome, Biome, double)} to the edges layer.
 */
@Mixin(AddEdgeBiomesLayer.class)
public class MixinAddEdgeBiomesLayer {
	@Inject(at = @At("HEAD"), method = "sample", cancellable = true)
	private void sample(LayerRandomnessSource rand, int north, int east, int south, int west, int center, CallbackInfoReturnable<Integer> info) {
		Biome centerBiome = Registry.BIOME.get(center);

		if (InternalBiomeData.getOverworldShores().containsKey(centerBiome) && InternalBiomeUtils.neighborsOcean(north, east, south, west)) {
			info.setReturnValue(Registry.BIOME.getRawId(InternalBiomeData.getOverworldShores().get(centerBiome).pickRandom(rand)));
		} else if (InternalBiomeData.getOverworldEdges().containsKey(centerBiome) && InternalBiomeUtils.isEdge(north, east, south, west, center)) {
			info.setReturnValue(Registry.BIOME.getRawId(InternalBiomeData.getOverworldEdges().get(centerBiome).pickRandom(rand)));
		}
	}
}
