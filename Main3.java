package BinarySearchTree;

public class Main3 {
        public static void main(String[] args) {
                BinarySearchTree bst = new BinarySearchTree();

                // Assert l'arbre est initialement vide
                assert bst.size() == 0 : "L'arbre devrait être vide initialement";

                // Test insertion
                bst.insert(50);
                bst.insert(30);
                bst.insert(70);
                assert bst.size() == 3 : "La taille de l'arbre devrait être 3 après trois insertions";

                // Test recherche - élément existant
                assert bst.search(bst.root, 30) != null : "Le noeud 30 devrait être trouvé";

                // Test recherche - élément non existant
                assert bst.search(bst.root, 100) == null : "Le noeud 100 ne devrait pas être trouvé";

                // Test suppression - supprimer feuille
                bst.remove(bst.root, 70);
                assert bst.search(bst.root, 70) == null : "Le noeud 70 devrait être supprimé";
                assert bst.size() == 2 : "La taille de l'arbre devrait être 2 après la suppression";

                // Test suppression - supprimer noeud avec un enfant
                bst.insert(20);
                bst.remove(bst.root, 30);
                assert bst.search(bst.root, 30) == null : "Le noeud 30 devrait être supprimé";
                assert bst.size() == 2 : "La taille de l'arbre devrait être 2 après la suppression";

                // Test suppression - supprimer noeud avec deux enfants
                bst.insert(40);
                bst.insert(60);
                bst.remove(bst.root, 50);
                assert bst.search(bst.root, 50) == null : "Le noeud 50 devrait être supprimé";
                assert bst.size() == 3 : "La taille de l'arbre devrait être 3 après la suppression";

                // Test suppression dans un arbre vide
                bst = new BinarySearchTree(); // Réinitialisation de l'arbre
                bst.remove(bst.root, 50);
                assert bst.size() == 0 : "La suppression dans un arbre vide ne devrait pas changer la taille";

                // Test insertion après suppression
                bst.insert(90);
                assert bst.size() == 1
                                : "La taille de l'arbre devrait être 1 après l'insertion dans un arbre précédemment vidé";

                System.out.println("Tous les tests ont réussi !");
        }

}