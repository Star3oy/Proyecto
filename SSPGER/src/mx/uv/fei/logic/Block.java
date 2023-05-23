package mx.uv.fei.logic;

/**
 *
 * @author Sue
 */
public class Block {
    private int idBlock;
    private int block;

    public void setIdBlock(int idBlock) {
        this.idBlock = idBlock;
    }

    public void setBlock(int block) {
        this.block = block;
    }
    
    public int getIdBlock() {
        return idBlock;
    }

    public int getBlock() {
        return block;
    }
    
    @Override
    public String toString() {
        return String.valueOf(block);
    }
}
