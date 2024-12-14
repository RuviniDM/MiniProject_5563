import java.util.Scanner;

class Main {
    // Method to allocate memory to blocks as per the First Fit algorithm
    static void firstFit(int blockSize[], int m, int processSize[], int n) {
        // Array to store the block id assigned to each process
        int allocation[] = new int[n];

        // Initially, no process is allocated to any block
        for (int i = 0; i < allocation.length; i++) {
            allocation[i] = -1;  // -1 indicates that the process has not been allocated
        }

        // Pick each process and find a suitable block for it
        for (int i = 0; i < n; i++) {
            boolean allocated = false;
            // Loop through all blocks to find the first block that fits the process
            for (int j = 0; j < m; j++) {
                // If the block size is greater than or equal to the process size
                if (blockSize[j] >= processSize[i]) {
                    // Allocate block j to process i
                    allocation[i] = j;

                    // Reduce the size of the allocated block by the process size
                    blockSize[j] -= processSize[i];

                    // Once a block is allocated, move on to the next process
                    allocated = true;
                    System.out.println("Process " + (i + 1) + " of size " + processSize[i] + " allocated to Block " + (j + 1) + ".");
                    break;
                }
            }
            if (!allocated) {
                System.out.println("Process " + (i + 1) + " of size " + processSize[i] + " not allocated (no suitable block found).");
            }
        }

        // Output the allocation table showing process numbers, sizes, and allocated blocks
        System.out.println("\nProcess No.\tProcess Size\tBlock No.");
        for (int i = 0; i < n; i++) {
            // Print the process number, its size, and the allocated block number (or "Not Allocated")
            System.out.print(" " + (i + 1) + "\t\t" + processSize[i] + "\t\t");
            if (allocation[i] != -1) {
                // If the process was allocated, print the block number (adding 1 to match user-friendly index)
                System.out.print((allocation[i] + 1));
            } else {
                // If no allocation, print "Not Allocated"
                System.out.print("Not Allocated");
            }
            System.out.println();
        }

        // Display the remaining sizes of all blocks after allocation
        System.out.println("\nRemaining Block Sizes:");
        for (int i = 0; i < m; i++) {
            // Print the block number and its remaining size after allocation
            System.out.println("Block " + (i + 1) + ": " + blockSize[i]);
        }
    }

    // Driver Code: Main method to take user input and trigger the allocation process
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input: Number of memory blocks
        System.out.println("Enter the number of blocks:");
        int m = getPositiveInput(scanner);
        int[] blockSize = new int[m];  // Array to store block sizes

        System.out.println("Enter block sizes:");
        for (int i = 0; i < m; i++) {
            blockSize[i] = getPositiveInput(scanner);  // Take block sizes as input
        }

        // Input: Number of processes
        System.out.println("Enter the number of processes:");
        int n = getPositiveInput(scanner);
        int[] processSize = new int[n];  // Array to store process sizes

        System.out.println("Enter process sizes:");
        for (int i = 0; i < n; i++) {
            processSize[i] = getPositiveInput(scanner);  // Take process sizes as input
        }

        // Call the firstFit method to allocate memory blocks to processes
        firstFit(blockSize, m, processSize, n);

        // Close the scanner to prevent resource leak
        scanner.close();
    }

    // Helper method to ensure the user enters a positive number
    public static int getPositiveInput(Scanner scanner) {
        int input;
        while (true) {
            input = scanner.nextInt();
            if (input <= 0) {
                System.out.println("Please enter a positive number.");
            } else {
                break;
            }
        }
        return input;
    }
}
