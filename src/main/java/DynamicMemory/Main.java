package DynamicMemory;//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.*;

public class Main {
    public static void main(String[] args) {

//        IMemoryAllocator allocator=new MemoryAllocator(1000,new FirstFitAlgorithm());
//        IMemoryAllocator allocator=new MemoryAllocator(1000,new FitAlgorithm(new BlockSorter(new AscComparer())));
        IMemoryAllocator allocator=new MemoryAllocator(1000,new FitAlgorithm(new BlockSorter(new DesComparer())));

//        IMemoryAllocator allocator=new MemoryAllocator(1000,new WorstFitAlgorithm());



        allocator.Allocate("A",50);
        allocator.Allocate("B",150);
        allocator.Allocate("C",120);
        allocator.Allocate("D",350);
        allocator.Allocate("E",60);
        allocator.Allocate("F",152);
        allocator.Allocate("G",18);

        allocator.ShowMemory();

        allocator.DeAllocate("C");
        allocator.DeAllocate("E");
//        allocator.DeAllocate("F");
//
        allocator.ShowMemory();

        allocator.Allocate("H",59);
        allocator.ShowMemory();

    }
}

interface IMemoryAllocator
{
    boolean Allocate(String objectName, int size);
    boolean DeAllocate(String objectName);

    boolean IsEmpty();

    boolean IsFull();

    void ShowMemory();
}

interface IAllocationAlgorithm
{
    MemoryBlock GetMemoryBlock(List<MemoryBlock> blocks, int size);
}

class MemoryBlock
{
    private int size;

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    private String object;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean IsFree()
    {
        return object==null;
    }
}

class MemoryAllocator implements IMemoryAllocator
{
    private int totalSize;
    private IAllocationAlgorithm algorithm;

    private List<MemoryBlock> blocks;

    public MemoryAllocator(int totalSize, IAllocationAlgorithm algorithm) {
        this.totalSize = totalSize;
        this.algorithm = algorithm;
        blocks=new ArrayList<MemoryBlock>();

        MemoryBlock block = new MemoryBlock();
        block.setSize(totalSize);
        blocks.add(block);
    }

    @Override
    public boolean Allocate(String objectName, int size) {
        MemoryBlock block = algorithm.GetMemoryBlock(blocks, size);

        if (block == null) {
            return false;
        }

        MemoryBlock newBlock = new MemoryBlock();
        newBlock.setSize(size);
        newBlock.setObject(objectName);
        int idxBlock = blocks.indexOf(block);
        blocks.add(idxBlock, newBlock);

        block.setSize(block.getSize() - size);



        if (block.getSize() == 0) {
            blocks.remove(block);
        }

        return true;
    }



    @Override
    public boolean DeAllocate(String objectName) {
        MemoryBlock block = SearchByName(objectName);

        if (block == null) {
            return false;
        }

        block.setObject(null);

        CheckAdjacency();
        return true;
    }

    private MemoryBlock SearchByName(String objectName) {
        for (MemoryBlock b : blocks) {
            if (objectName.equals(b.getObject())) {
                return b;
            }
        }

        return null;
    }

    private void CheckAdjacency() {


        for(int i=0;i<blocks.size() ;i++){

            if(i == blocks.size()-1) {
                return;
            }

            if (blocks.get(i).IsFree() && blocks.get(i+1).IsFree()){
                MemoryBlock currentBlock = blocks.get(i);
                currentBlock.setSize(currentBlock.getSize() + blocks.get(i+1).getSize());
                blocks.remove(i+1);
                i -= 1;
            }
        }

    }

    @Override
    public boolean IsEmpty() {
        return false;
    }

    @Override
    public boolean IsFull() {
        return false;
    }

    @Override
    public void ShowMemory() {
        for (MemoryBlock m : blocks) {
            System.out.println(m.getObject() + " " + m.getSize());
        }

        System.out.println("-----------------------");
    }
}

class FirstFitAlgorithm implements IAllocationAlgorithm
{
    @Override
    public MemoryBlock GetMemoryBlock(List<MemoryBlock> blocks, int size) {

        for (MemoryBlock b : blocks) {
            if (b.IsFree() && b.getSize() >= size) {
                return b;
            }
        }

        return null;
    }
}

class FitAlgorithm implements IAllocationAlgorithm
{
    private BlockSorter blockSorter ;
    public FitAlgorithm(BlockSorter blockSorter){
        this.blockSorter = blockSorter;
    }
    @Override
    public MemoryBlock GetMemoryBlock(List<MemoryBlock> blocks, int size) {
        List<MemoryBlock> emptyBlocks = EmptyBlocks.emptyBlocks(blocks);

        blockSorter.sort(emptyBlocks);

        for (MemoryBlock b : emptyBlocks) {
            if (b.getSize() >= size) {
                return b;
            }
        }
        return null;
    }

}


class EmptyBlocks
{

    public static List<MemoryBlock> emptyBlocks(List<MemoryBlock> blocks){
        List<MemoryBlock> emptyBlocks = new ArrayList<MemoryBlock>();
        for (MemoryBlock b : blocks) {
            if (b.IsFree()) {
                emptyBlocks.add(b);
            }
        }
        return emptyBlocks;
    }

}