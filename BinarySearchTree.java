package BinarySearchTree;
/* Java: Créer une nouvelle version de l’arbre binaire de recherche (BinarySearchTree). Cette mise en œuvre devrait inclure les opérations standard d’un arbre binaire de recherche : size(), isEmpty(), insert(int key), getMax(), search(int key) and remove(int key).
Ajoutez la liste des fonctions suivante:
1. (1.5pt) Implémentez la fonction void updateBST(Node root) qui permet de modifier un arbre de recherche binaire, de telle sorte que la clé de chaque nœud soit mis à jour pour contenir la somme de toutes les clés supérieures à lui présentes dans le BST.
2. (2pts) Implémentez la fonction boolean areSameBST(int[] array1, int[] array2) qui vérifie si deux tableaux d’entiers, qui sont des séquences de clés pour un arbre binaire de recherche (BST), définissent le même BST sans nécessiter la construction de l’arbre lui-même. On part du principe que l’insertion des clés dans le BST suit l’ordre présenté dans les tableaux.
3. (1.5pt) Déterminez la complexité temporelle de chaque fonction
*/

public class BinarySearchTree {
    class Node {
        int key;
        Node left, right;

        public Node(int item) {
            key = item;
            left = right = null;
        }
    }

    Node root;

    BinarySearchTree() {
        root = null;
    }

    // Fonction pour insert nœud
    public void insert(int key) {
        root = insertRec(root, key);
    }

    // Fonction récursive insert
    Node insertRec(Node root, int key) {
        // Si arbre vide, noeud est racine
        if (root == null) {
            root = new Node(key);
            return root;
        }

        // Sinon, on parcourt l'arbre pour trouver l'emplacement du nouveau noeud
        if (key < root.key)
            root.left = insertRec(root.left, key);
        else if (key > root.key)
            root.right = insertRec(root.right, key);

        return root;
    }

    // Fonction helper pour size (argument permet récursivté)
    private int sizeHelper(Node node) {
        if (node == null) {
            return 0;
        } else {
            return (sizeHelper(node.left) + 1 + sizeHelper(node.right));
        }
    }

    // Fonction size (au cas ou size ne doit absolument pas prendre d'arguments)
    public int size() {
        return sizeHelper(root);
    }

    public Node search(Node root, int key) {
        // Si arbre vide ou clé est racine
        if (root == null || root.key == key)
            return root;

        // Si supérieure à clé, recherche sous-arbre droit
        if (root.key < key)
            return search(root.right, key);

        // Sinon recherche dans sous-arbre gauche
        return search(root.left, key);
    }

    // Fonction supprimer noeud
    public Node remove(Node root, int key) {
        // Si arbre vide, return null
        if (root == null)
            return root;
        // Parcours arbre pour trouver noeud
        if (key < root.key)
            root.left = remove(root.left, key);
        else if (key > root.key)
            root.right = remove(root.right, key);
        else {
            // Cas 1: noeud sans enfant
            if (root.left == null && root.right == null)
                root = null;

            // Cas 2: noeud avec un seul enfant
            else if (root.left == null)
                root = root.right;
            else if (root.right == null)
                root = root.left;

            // Cas 3: noeud avec deux enfants
            else {
                // Trouver le successeur inorder
                Node successor = minValueNode(root.right);
                root.key = successor.key;
                root.right = remove(root.right, successor.key);
            }
        }
        return root;
    }

    // Fonction pour trouver noeud minimum (utilisé dans cas 3 de remove)
    Node minValueNode(Node node) {
        Node current = node;
        // On cherche feuille plus à gauche
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    public void updateBST(Node root) {
        // Appeler fonction récursive avec 0
        updateBSTRec(root, new int[] { 0 });
    }

    // Fonction récursive pour mettre à jour l'arbre
    private void updateBSTRec(Node node, int[] sum) {
        // Si noeud null, return
        if (node == null) {
            return;
        }
        updateBSTRec(node.right, sum);
        sum[0] = sum[0] + node.key;
        node.key = sum[0];
        updateBSTRec(node.left, sum);
    }

    public boolean areSameBST(int[] array1, int[] array2) {
        // Vériofication rapide des longueurs
        if (array1.length != array2.length) {
            return false;
        }
        // Si tableaux sont vides, définissent même BST (arbre vide)
        if (array1.length == 0) {
            return true;
        }
        // Puisque ordre d'insertion respecté, racines comparées
        if (array1[0] != array2[0]) {
            return false;
        }

        // Extraction des sous-tableaux gauche et droit
        int[] left1 = getLeftSubtree(array1, array1[0]);
        int[] right1 = getRightSubtree(array1, array1[0]);
        int[] left2 = getLeftSubtree(array2, array2[0]);
        int[] right2 = getRightSubtree(array2, array2[0]);

        // Récursion pour sous-tableaux
        return areSameBST(left1, left2) && areSameBST(right1, right2);
    }

    private int[] getLeftSubtree(int[] array, int root) {
        int leftCount = 0;
        // Compter nbre éléments sous-arbre gauche
        for (int j : array) {
            if (j < root) {
                leftCount++;
            }
        }

        int[] leftSubtree = new int[leftCount];
        int index = 0;
        // Remplir sous-tableau gauche
        for (int j : array) {
            if (j < root) {
                leftSubtree[index++] = j;
            }
        }

        return leftSubtree;
    }

    private int[] getRightSubtree(int[] array, int root) {
        int rightCount = 0;
        // Compter nbre éléments sous-arbre droit
        for (int j : array) {
            if (j > root) {
                rightCount++;
            }
        }

        int[] rightSubtree = new int[rightCount];
        int index = 0;
        // Remplir sous-tableau droit
        for (int j : array) {
            if (j > root) {
                rightSubtree[index++] = j;
            }
        }

        return rightSubtree;
    }
}