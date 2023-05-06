<?php
	session_start(); 
	require_once("controleur/config_bdd.php"); 
	require_once("controleur/controleur.class.php"); 
	//intancier ma classe Controleur 
	$unControleur = new Controleur($serveur, $bdd, $user, $mdp);
  // Vérifier si l'utilisateur est connecté
  
  // if (isset($_SESSION['user_id'])) {
  //     // L'utilisateur est connecté, afficher l'élément
  //     echo "<p>Bienvenue, " . $_SESSION['user_nom'] . "!</p>";
  // } else {
  //     // L'utilisateur n'est pas connecté, ne pas afficher l'élément
  //     echo "<p>Vous n'êtes pas connecté.</p>";
  // }

  
// var_dump($_SESSION['id_client']);
// var_dump($_SESSION['user_id']);
 
  
  
  if(isset($_POST['inscription'])){
    $unControleur->createAccount($_POST);
  }

//   if(isset($_POST['resa'])){
//     $unControleur->take_reservations($_POST);
// }



if(isset($_POST['resa'])){
  $_POST['destination_id'] = $_SESSION['destination'];
$_POST['date_depart'] = $_SESSION['date_depart'];
$_POST['date_retour'] = $_SESSION['date_retour'];
$_POST['nb_personnes'] = $_SESSION['nombre_voyageurs'];
$_POST['logement_id'] = $_SESSION['logement'];
$_POST['transport_id'] = $_SESSION['transport'];
$_POST['reservations_prix'] = $_SESSION['prix_total'];
$_POST['id_client'] = $_SESSION['id_client'];
$_POST['email'] = $_SESSION['email'];

  $unControleur->take_reservations(
      null,
      $_POST['destination_id'],
      $_POST['date_depart'],
      $_POST['date_retour'],
      $_POST['nb_personnes'],
      $_POST['logement_id'],
      $_POST['transport_id'],
      $_POST['reservations_prix'],
      $_POST['id_client'],
      $_POST['email']
  );
}


  // if(isset($_POST['connexion'])){
  //   $unControleur->checkLogin($_POST);
  // }

  if(isset($_POST['connexion'])){
    $email = $_POST['email'];
    $mdp = $_POST['mdp'];
    $resultat = $unControleur->checkLogin($email, $mdp);
    if (!$resultat) {
        // La connexion a réussi, stocker les informations de l'utilisateur dans la session
        $_SESSION['user_id'] = $resultat['id_client'];
        $_SESSION['user_nom'] = $resultat['nom'];
        $_SESSION['user_email'] = $resultat['email'];
        // Rediriger l'utilisateur vers la page de destinations
        header('Location: index.php?page=2');
    } else {
        // La connexion a échoué, afficher un message d'erreur
        $erreur = "Email ou mot de passe incorrect.";
    }
}





?>

<!doctype html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="style.css">
  <link href="https://cdnjs.cloudflare.com/ajax/libs/flowbite/1.6.3/flowbite.min.css"  rel="stylesheet" />
  <script src="https://cdn.tailwindcss.com"></script>
  <script src="https://cdn.jsdelivr.net/npm/tw-elements/dist/js/index.min.js"></script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" />
  <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700&display=swap" />
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/tw-elements/dist/css/index.min.css" />
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flowbite/css/flowbite.min.css">
<script src="https://cdn.jsdelivr.net/npm/flowbite/js/flowbite.min.js"></script>

  <title>Travelin</title>
</head>
<body>

  
 
<nav class="bg-white px-2 sm:px-4 py-2.5 dark:bg-gray-900 fixed w-full z-20 top-0 left-0 border-b border-gray-200 dark:border-gray-600">
 <div class="container flex flex-wrap items-center justify-between mx-auto">
 <a href="#" class="flex items-center">
     <img src="travelin.png" class="h-6 mr-3 sm:h-9" alt="Flowbite Logo">
     <span class="self-center text-xl font-semibold whitespace-nowrap dark:text-white">Travelin</span>
 </a>
 <div class="flex md:order-2">

  <button class="data-modal-target="authentication-modal" data-modal-toggle="authentication-modal" type="button">
    <ion-icon name="person-circle-outline" class="text-4xl block py-2 pl-3 pr-4 text-gray-700 rounded hover:bg-gray-100 md:hover:bg-transparent md:hover:text-blue-700 md:p-0 md:dark:hover:text-white dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent dark:border-gray-700"></ion-icon>
  </button>
     <button data-collapse-toggle="navbar-sticky" type="button" class="inline-flex items-center p-2 text-sm text-gray-500 rounded-lg md:hidden hover:bg-gray-100 focus:outline-none focus:ring-2 focus:ring-gray-200 dark:text-gray-400 dark:hover:bg-gray-700 dark:focus:ring-gray-600" aria-controls="navbar-sticky" aria-expanded="false">
       <span class="sr-only">Fermer</span>
       <svg class="w-6 h-6" aria-hidden="true" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M3 5a1 1 0 011-1h12a1 1 0 110 2H4a1 1 0 01-1-1zM3 10a1 1 0 011-1h12a1 1 0 110 2H4a1 1 0 01-1-1zM3 15a1 1 0 011-1h12a1 1 0 110 2H4a1 1 0 01-1-1z" clip-rule="evenodd"></path></svg>
   </button>
 </div>
 <div class="items-center justify-between hidden w-full md:flex md:w-auto md:order-1" id="navbar-sticky">
   <ul class="flex flex-col p-4 mt-4 border border-gray-100 rounded-lg bg-gray-50 md:flex-row md:space-x-8 md:mt-0 md:text-sm md:font-medium md:border-0 md:bg-white dark:bg-gray-800 md:dark:bg-gray-900 dark:border-gray-700">
     <li>
       <a href="index.php?page=0" class="block py-2 pl-3 pr-4 text-gray-700 rounded hover:bg-gray-100 md:hover:bg-transparent md:hover:text-blue-700 md:p-0 md:dark:hover:text-white dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent dark:border-gray-700" >Accueuil</a>
     </li>
     <!-- <li>
       <a href="index.php?page=1" class="block py-2 pl-3 pr-4 text-gray-700 rounded hover:bg-gray-100 md:hover:bg-transparent md:hover:text-blue-700 md:p-0 md:dark:hover:text-white dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent dark:border-gray-700">Promotions</a>
     </li> -->
     <li>
       <a href="index.php?page=2" class="block py-2 pl-3 pr-4 text-gray-700 rounded hover:bg-gray-100 md:hover:bg-transparent md:hover:text-blue-700 md:p-0 md:dark:hover:text-white dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent dark:border-gray-700">Destinations</a>
     </li>
     <?php if (isset($_SESSION['id_client'])) { ?>
      <li>
       <a href="index.php?page=3" class="block py-2 pl-3 pr-4 text-gray-700 rounded hover:bg-gray-100 md:hover:bg-transparent md:hover:text-blue-700 md:p-0 md:dark:hover:text-white dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent dark:border-gray-700">Mes reservations</a>
     </li>
     <li>
       <a href="index.php?page=5" class="block py-2 pl-3 pr-4 text-gray-700 rounded hover:bg-gray-100 md:hover:bg-transparent md:hover:text-blue-700 md:p-0 md:dark:hover:text-white dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent dark:border-gray-700">Déconnection</a>
     </li>
      <?php } ?>
     <li>
       <a href="" class="block py-2 pl-3 pr-4 text-gray-700 rounded hover:bg-gray-100 md:hover:bg-transparent md:hover:text-blue-700 md:p-0 md:dark:hover:text-white dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent dark:border-gray-700">Contact</a>
     </li>
   </ul>
 </div>
 </div>
</nav>



<!-- login form en popup -->
<div id="authentication-modal" tabindex="-1" aria-hidden="true" class="fixed top-0 left-0 right-0 z-50 hidden w-full p-4 overflow-x-hidden overflow-y-auto md:inset-0 h-modal md:h-full">
  <div class="relative w-full h-full max-w-md md:h-auto">
      <!-- Modal content -->
      <div class="relative bg-white rounded-lg shadow dark:bg-gray-700">
          <button type="button" class="absolute top-3 right-2.5 text-gray-400 bg-transparent hover:bg-gray-200 hover:text-gray-900 rounded-lg text-sm p-1.5 ml-auto inline-flex items-center dark:hover:bg-gray-800 dark:hover:text-white" data-modal-hide="authentication-modal">
              <svg aria-hidden="true" class="w-5 h-5" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M4.293 4.293a1 1 0 011.414 0L10 8.586l4.293-4.293a1 1 0 111.414 1.414L11.414 10l4.293 4.293a1 1 0 01-1.414 1.414L10 11.414l-4.293 4.293a1 1 0 01-1.414-1.414L8.586 10 4.293 5.707a1 1 0 010-1.414z" clip-rule="evenodd"></path></svg>
              <span class="sr-only">Fermer</span>
          </button>
          <div class="px-6 py-6 lg:px-8">
              <h3 class="mb-4 text-xl font-medium text-gray-900 dark:text-white">Connexion</h3>
              <form class="space-y-6" method='POST'>
                  <div>
                      <label for="email" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Email</label>
                      <input type="email" name="email" id="email" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-600 dark:border-gray-500 dark:placeholder-gray-400 dark:text-white" placeholder="nom.prénom@email.com" required>
                  </div>
                  <div>
                      <label for="password" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Mot de passe</label>
                      <input type="password" name="mdp" id="mdp" placeholder="••••••••" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-600 dark:border-gray-500 dark:placeholder-gray-400 dark:text-white" required>
                  </div>
                  <div class="flex justify-between">
                      <div class="flex items-start">
                          <div class="flex items-center h-5">
                              <input id="remember" type="checkbox" value="" class="w-4 h-4 border border-gray-300 rounded bg-gray-50 focus:ring-3 focus:ring-blue-300 dark:bg-gray-600 dark:border-gray-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 dark:focus:ring-offset-gray-800">
                          </div>
                          <label for="remember" class="ml-2 text-sm font-medium text-gray-900 dark:text-gray-300">Se souvenir de moi</label>
                      </div>
                      <a href="#" class="text-sm text-blue-700 hover:underline dark:text-blue-500">Mot de passe oublier ?</a>
                  </div>
                  <button type="submit" name="connexion" class="w-full text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">connexion</button>

                  <div class="text-sm font-medium text-gray-500 dark:text-gray-300">
                      Pas encore inscrit ? <a class="data-modal-target="registration-modal" data-modal-toggle="registration-modal" data-modal-hide="authentication-modal">Créer un compte</a>
                  </div>
              </form>
          </div>
      </div>
  </div>
</div> 
<!--  -->

<!-- register form en popup -->
<div id="registration-modal" tabindex="-1" aria-hidden="true" class="fixed top-0 left-0 right-0 z-50 hidden w-full p-4 overflow-x-hidden overflow-y-auto md:inset-0 h-modal md:h-full">
  <div class="relative w-full h-full max-w-md md:h-auto">
      <!-- Modal content -->
      <div class="relative bg-white rounded-lg shadow dark:bg-gray-700">
          <button type="button" class="absolute top-3 right-2.5 text-gray-400 bg-transparent hover:bg-gray-200 hover:text-gray-900 rounded-lg text-sm p-1.5 ml-auto inline-flex items-center dark:hover:bg-gray-800 dark:hover:text-white" data-modal-hide="registration-modal">
              <svg aria-hidden="true" class="w-5 h-5" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M4.293 4.293a1 1 0 011.414 0L10 8.586l4.293-4.293a1 1 0 111.414 1.414L11.414 10l4.293 4.293a1 1 0 01-1.414 1.414L10 11.414l-4.293 4.293a1 1 0 01-1.414-1.414L8.586 10 4.293 5.707a1 1 0 010-1.414z" clip-rule="evenodd"></path></svg>
              <span class="sr-only">Fermer</span>
          </button>
          <div class="px-6 py-6 lg:px-8">
              <h3 class="mb-4 text-xl font-medium text-gray-900 dark:text-white">Inscription</h3>
              <br>
              <form class="space-y-6" method="post">

                  <div class="grid md:grid-cols-2 md:gap-6">
                        <div class="form-check form-check-inline">
                          <input class="form-check-input" type="radio" name="genre" id="homme" value="homme">
                          <fb-label class="form-check-label" for="male">
                            Homme
                          </fb-label>
                        </div>
                        <div class="form-check form-check-inline">
                          <input class="form-check-input" type="radio" name="genre" id="femme" value="femme">
                          <fb-label class="form-check-label" for="female">
                            Femme
                          </fb-label>
                        </div>
                  </div>

                  <div class="grid md:grid-cols-2 md:gap-6">
                  <div>
                      <label for="nom" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Nom</label>
                      <input type="text" name="nom" id="nom" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-600 dark:border-gray-500 dark:placeholder-gray-400 dark:text-white" placeholder="Nom" required>
                  </div>
                  <div>
                      <label for="prenom" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Prénom</label>
                      <input type="text" name="prenom" id="prenom" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-600 dark:border-gray-500 dark:placeholder-gray-400 dark:text-white" placeholder="Prénom" required>
                  </div>
                  </div>

                  <div class="grid md:grid-cols-2 md:gap-6">
                  <div>
                      <label for="email" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Email</label>
                      <input type="email" name="email" id="email" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-600 dark:border-gray-500 dark:placeholder-gray-400 dark:text-white" placeholder="nom.prénom@email.com" required>
                  </div>
                  <div>
                      <label for="telephone" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Téléphone</label>
                      <input type="tel" name="telephone" id="telephone" pattern="[0-9]{10}" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-600 dark:border-gray-500 dark:placeholder-gray-400 dark:text-white" placeholder="Téléphone" required>
                  </div>
                  </div>

                  <div>
                      <label for="password" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Mot de passe</label>
                      <input type="password" name="mdp" id="mdp" placeholder="••••••••" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-600 dark:border-gray-500 dark:placeholder-gray-400 dark:text-white" required>
                  </div>
                  <!-- <div>
                      <label for="password" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Mot de passe</label>
                      <input type="password" name="password" id="password-verif" placeholder="••••••••" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-600 dark:border-gray-500 dark:placeholder-gray-400 dark:text-white" required>
                  </div> -->
                  <button type="submit" name="inscription" class="w-full text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">inscription</button>

                  <div class="text-sm font-medium text-gray-500 dark:text-gray-300">
                      Déjà inscrit ? <a href="" class="data-modal-target="authentication-modal" data-modal-toggle="authentication-modal" data-modal-hide="registration-modal">Se connecter</a>
                  </div>
              </form>
          </div>
      </div>
  </div>
</div> 


<!--  -->
 
<footer class="fixed bottom-0 left-0 z-20 w-full p-4 bg-white border-t border-gray-200 shadow md:flex md:items-center md:justify-between md:p-6 dark:bg-gray-800 dark:border-gray-600">
 <span class="text-sm text-gray-500 sm:text-center dark:text-gray-400">© 2023 <a href="https://flowbite.com/" class="hover:underline">Travelin™</a>. Tous droits réservé.
 </span>
 <ul class="flex flex-wrap items-center mt-3 text-sm text-gray-500 dark:text-gray-400 sm:mt-0">
     <li>
         <a href="#" class="mr-4 hover:underline md:mr-6 ">A propos</a>
     </li>
     <li>
         <a href="#" class="mr-4 hover:underline md:mr-6">Conditions Générale d'utilisation</a>
     </li>
     <li>
         <a href="#" class="mr-4 hover:underline md:mr-6">Conditions Générale de Vente</a>
     </li>
     <li>
         <a href="#" class="hover:underline">Contact</a>
     </li>
 </ul>
</footer>


<script src="https://cdnjs.cloudflare.com/ajax/libs/flowbite/1.6.3/flowbite.min.js"></script>
<script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
</body>
</html>

<?php



if(isset($_GET['page'])){
  $page = $_GET['page'];
 }else {
  $page = 0 ;
 }
 switch ($page){
  case 0 : require_once("home.php"); break;
  case 1 : require_once("vue/resa.php"); break;
  case 2 : require_once("destinations.php"); break;
  case 3 : require_once("reservations.php"); break;
  case 4 : require_once("register.php"); break;
  case 6 : require_once("connect.php"); break;
  case 5 : 
    session_destroy();
    unset($_SESSION);
    header('location:index.php?page=0');
    exit();
  default : require_once("erreur_404.php"); break;
 }

?>