package mekanism.common.block.attribute;

public class Attributes {

    /** If a block supports security. */
    public static class AttributeSecurity implements Attribute {}

    /** If a block has an inventory. */
    public static class AttributeInventory implements Attribute {}

    /** If a block supports rotation on all faces. */
    public static class AttributeFullRotation implements Attribute {}

    /** If a block supports comparators. */
    public static class AttributeComparator implements Attribute {}

    /** If a block has a redstone input configuration. */
    public static class AttributeRedstone implements Attribute {}

    /** If a block can emit redstone. */
    public static class AttributeRedstoneEmitter implements Attribute {}

    /** Custom explosion resistance attribute. */
    public static class AttributeCustomResistance implements Attribute {

        private float resistance;

        public AttributeCustomResistance(float resistance) {
            this.resistance = resistance;
        }

        public float getResistance() {
            return resistance;
        }
    }
}
