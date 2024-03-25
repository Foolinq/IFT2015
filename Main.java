package BinarySearchTree;

public class Main {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();

        // Insertion des valeurs dans le BST
        bst.insert(5);
        bst.insert(2);
        bst.insert(13);

        // Affichage de l'arbre BST avant la mise à jour
        System.out.println("BST avant mise à jour :");
        printInOrder(bst.root);

        // Mise à jour du BST
        bst.updateBST(bst.root);

        // Affichage de l'arbre BST après la mise à jour
        System.out.println("\nBST après mise à jour :");
        printInOrder(bst.root);

        // Test avec assertions pour updateBST
        assert bst.search(bst.root, 18) != null : "Le noeud avec la clé 18 (anciennement 13) devrait exister";
        assert bst.search(bst.root, 20) != null : "Le noeud avec la clé 20 (anciennement 5) devrait exister";
        assert bst.search(bst.root, 2) != null : "Le noeud avec la clé 2 devrait toujours exister";

        // Test avec assertions pour areSameBST
        int[] array1 = { 5, 2, 13, 1, 1, 3 };
        int[] array2 = { 5, 13, 2, 1, 1, 1 };
        int[] array3 = { 5, 2, 12, 1, 1, 3 };

        // Devraient être considérés comme le même BST
        assert bst.areSameBST(array1, array2) : "Les tableaux array1 et array2 devraient définir le même BST";

        // Ne devraient pas être considérés comme le même BST
        assert !bst.areSameBST(array1, array3) : "Les tableaux array1 et array3 ne devraient pas définir le même BST";

        System.out.println("\nTous les tests ont réussi !");
    }

    // Méthode helper pour afficher l'arbre en ordre
    private static void printInOrder(BinarySearchTree.Node node) {
        if (node != null) {
            printInOrder(node.left);
            System.out.print(node.key + " ");
            printInOrder(node.right);
        }
    }
}