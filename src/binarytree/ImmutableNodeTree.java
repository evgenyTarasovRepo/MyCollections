package binarytree;

public class ImmutableNodeTree implements INode{
    private final int value;
    private final INode left;
    private final INode right;

    public ImmutableNodeTree(int value, INode left, INode right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    @Override
    public INode add(Integer value) {
        if (value == this.value) return this;
        if (value < this.value) {
            if (left == null) {
                return new ImmutableNodeTree(this.value, new ImmutableNodeTree(value, null, null), right);
            }
            return new ImmutableNodeTree(this.value, left.add(value), right);
        }
        if (right == null) {
            return new ImmutableNodeTree(this.value, new ImmutableNodeTree(value, null, null), left);
        }
        return new ImmutableNodeTree(this.value, left, right.add(value));
    }

    @Override
    public void print() {
        if (left != null ) left.print();
        System.out.println(value);
        if (right != null) right.print();
    }

    @Override
    public int count() {
        int leftCount = left != null ? left.count() : 0;
        int rightCount = right != null ? right.count() : 0;
        return leftCount + 1 + rightCount;
    }
}
