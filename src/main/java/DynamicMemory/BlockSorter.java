package DynamicMemory;

import java.util.List;

class BlockSorter {
    IBlockComparer comparer;
    public BlockSorter(IBlockComparer comparer){
        this.comparer = comparer;
    }
    public void  sort(List<MemoryBlock> emptyBlocks) {


        for (int i = 0; i < emptyBlocks.size() - 1; i++){
            for (int j = 0; j < emptyBlocks.size() - i - 1; j++){
                if (comparer.compare(emptyBlocks.get(j), emptyBlocks.get(j+1)) > 0){
                    MemoryBlock temp = emptyBlocks.get(j);
                    emptyBlocks.set(j, emptyBlocks.get(j + 1));
                    emptyBlocks.set(j + 1, temp);
                }
            }
        }
    }
}

interface IBlockComparer {
    int compare(MemoryBlock block1, MemoryBlock block2);
}

class AscComparer implements IBlockComparer{

    @Override
    public int compare(MemoryBlock block1, MemoryBlock block2) {
        return block1.getSize()-block2.getSize();
    }
}

class DesComparer implements IBlockComparer{

    @Override
    public int compare(MemoryBlock block1, MemoryBlock block2) {
        return block2.getSize()-block1.getSize();
    }
}