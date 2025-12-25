package hibi.blahaj.block;

import com.mojang.serialization.*;
import hibi.blahaj.sound.*;
import net.minecraft.core.*;
import net.minecraft.sounds.*;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.entity.projectile.*;
import net.minecraft.world.item.context.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.phys.*;
import net.minecraft.world.phys.shapes.*;
import org.jspecify.annotations.*;

public class CuddlyBlock extends HorizontalDirectionalBlock {

	protected static final VoxelShape SHAPE = Block.box(4.0, 0.0, 4.0, 12.0, 8.0, 12.0);
	public static final MapCodec<CuddlyBlock> CODEC = simpleCodec(CuddlyBlock::new);

	public CuddlyBlock(Properties settings) {
		super(settings);
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
	}

	@Override
	protected @NonNull MapCodec<? extends HorizontalDirectionalBlock> codec() {
		return CODEC;
	}

	public @NonNull VoxelShape getShape(@NonNull BlockState state, @NonNull BlockGetter world, @NonNull BlockPos pos, @NonNull CollisionContext context) {
		return SHAPE;
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext ctx) {
		return this.defaultBlockState().setValue(FACING, ctx.getHorizontalDirection().getOpposite());
	}

	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}

	@Override
	protected void onProjectileHit(Level world, @NonNull BlockState state, BlockHitResult hit, @NonNull Projectile projectile) {
		world.playSound(null, hit.getBlockPos(), BlahajSoundEvents.BLOCK_CUDDLY_ITEM_HIT, SoundSource.BLOCKS, 0.5f, 1);
		super.onProjectileHit(world, state, hit, projectile);
	}

	@Override
	protected @NonNull InteractionResult useWithoutItem(@NonNull BlockState state, Level world, @NonNull BlockPos pos, @NonNull Player player, @NonNull BlockHitResult hit) {
		world.playSound(null, pos, BlahajSoundEvents.getRandomSqueak(world.getRandom()), SoundSource.BLOCKS, 0.5f, 1);
		return InteractionResult.SUCCESS;
	}

}
