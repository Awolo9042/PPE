<?php
ob_start();

if (!isset($_SESSION['id_client'])) {
    header("Location: index.php");
    exit();
}


require_once("controleur/config_bdd.php"); 
require_once("controleur/controleur.class.php"); 
//intancier ma classe Controleur 
$unControleur = new Controleur($serveur, $bdd, $user, $mdp);

// var_dump($_SESSION);


// Initialiser les variables nécessaires
$erreur = '';
$etape = 1;

if (isset($_GET['destination_id'])) {
    // Récupérer l'identifiant de destination
    $destination_id = $_GET['destination_id'];
}

if (isset($_POST['form1_submit'])) {
    $destination = $_POST['destination'];
    if (!empty($destination)) {
        $_SESSION['destination'] = $destination; // Stocker le nom dans la session
        $etape = 2; // Passer à l'étape suivante
    } else {
        $erreur = "Erreur.";
    }
} elseif (isset($_POST['form2_submit'])) {
    $date_depart = $_POST['date_depart'];
    $date_retour = $_POST['date_retour'];
    if (!empty($date_depart) && !empty($date_retour)) {
        $_SESSION['date_depart'] = $date_depart; 
        $_SESSION['date_retour'] = $date_retour; 
        $etape = 3; // Passer à l'étape suivante
    } else {
        $erreur = "Erreur.";
    }
} elseif (isset($_POST['form3_submit'])) {
    $nombre_voyageurs = $_POST['nombre_voyageurs'];
    if (!empty($nombre_voyageurs)) {
        $_SESSION['nombre_voyageurs'] = $nombre_voyageurs; 
        $etape = 4; // Passer à l'étape suivante
    } else {
        $erreur = "Erreur.";
    }
} elseif (isset($_POST['form4_submit'])) {
    $transport = $_POST['transport'];
    if (!empty($transport)) {
        $_SESSION['transport'] = $transport; 
        $etape = 5; // Passer à l'étape suivante
    } else {
        $erreur = "Erreur.";
    }
} elseif (isset($_POST['form5_submit'])) {
    $logement = $_POST['logement'];
    if (!empty($logement)) {
        $_SESSION['logement'] = $logement; 
        $etape = 6; // Passer à l'étape suivante
    } else {
        $erreur = "Erreur.";
    }
} elseif (isset($_POST['form6_submit'])) {
    $etape = 7; // Passer à l'étape suivante
}



    
?>

<!-- Afficher le formulaire en fonction de l'étape actuelle -->
<!DOCTYPE html>
<html>
<head>
  
</head>
<body>

<style>
  div#first {
    float: left;
    margin-left: 20px;
  }
</style>

<div class="container my-5">

    <div class="card">
        <div class="card-body">
            <!-- afficher la barre de progression -->


            <?php if ($etape == 1): ?>
                <center>
                <div id="first">
              
              <ol class="space-y-4 w-72">
                  <li>
                  <div class="w-full p-4 text-blue-700 bg-blue-100 border border-blue-300 rounded-lg dark:bg-gray-800 dark:border-blue-800 dark:text-blue-400" role="alert">
                          <div class="flex items-center justify-between">
                              <span class="sr-only"></span>
                              <h3 class="font-medium">1. Destination</h3>
                              <svg aria-hidden="true" class="w-5 h-5" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M12.293 5.293a1 1 0 011.414 0l4 4a1 1 0 010 1.414l-4 4a1 1 0 01-1.414-1.414L14.586 11H3a1 1 0 110-2h11.586l-2.293-2.293a1 1 0 010-1.414z" clip-rule="evenodd"></path></svg>
                          </div>
                      </div>
                  </li>
                  <li>
                  <div class="w-full p-4 text-gray-900 bg-gray-100 border border-gray-300 rounded-lg dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400" role="alert">
                          <div class="flex items-center justify-between">
                              <span class="sr-only"></span>
                              <h3 class="font-medium">2. Choix des dates</h3>
                            </div>
                      </div>
                  </li>
                  <li>
                  <div class="w-full p-4 text-gray-900 bg-gray-100 border border-gray-300 rounded-lg dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400" role="alert">
                          <div class="flex items-center justify-between">
                              <span class="sr-only"></span>
                              <h3 class="font-medium">3. Nombres de personnes</h3>
                            </div>
                      </div>
                  </li>
                  <li>
                      <div class="w-full p-4 text-gray-900 bg-gray-100 border border-gray-300 rounded-lg dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400" role="alert">
                          <div class="flex items-center justify-between">
                              <span class="sr-only"></span>
                              <h3 class="font-medium">4. Choix du transport</h3>
                          </div>
                      </div>
                  </li>
                  </li>
                      <li>
                      <div class="w-full p-4 text-gray-900 bg-gray-100 border border-gray-300 rounded-lg dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400" role="alert">
                          <div class="flex items-center justify-between">
                              <span class="sr-only"></span>
                              <h3 class="font-medium">5. Choix du logement</h3>
                          </div>
                      </div>
                  </li>
                  <li>
                      <div class="w-full p-4 text-gray-900 bg-gray-100 border border-gray-300 rounded-lg dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400" role="alert">
                          <div class="flex items-center justify-between">
                              <span class="sr-only"></span>
                              <h3 class="font-medium">6. Récapitulatif</h3>
                          </div>
                      </div>
                  </li>
                  <li>
                      <div class="w-full p-4 text-gray-900 bg-gray-100 border border-gray-300 rounded-lg dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400" role="alert">
                          <div class="flex items-center justify-between">
                              <span class="sr-only"></span>
                              <h3 class="font-medium">7. Payement</h3>
                          </div>
                      </div>
                  </li>
              </ol>
              
                          </div>
                <form method="post" class="form">
                <div class="w-full max-w-sm bg-white border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700">
            <br>
            <div class="px-5 pb-5 second">
                <a href="#">
                    <h5 class="text-xl font-semibold tracking-tight text-gray-900 dark:text-white">Destination :</h5>
                   
                    <select name='destination' class='block w-full p-2 mb-6 text-sm text-gray-900 border border-gray-300 rounded-lg bg-gray-50 focus:ring-blue-500 focus:border-blue-500 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500'>
                    <?php 
                            // $destinations = $unControleur->getDestinations();

                            // foreach($destinations as $destination) {
                            //     echo "<option value='".$destination['destination_id']."'>".$destination['destination_name']." - ".$destination['destination_prix']." €</option>";
                            // }
                            
                        $destinations = $unControleur->getDestinations();
                        foreach($destinations as $destination) {
                            // Pré-sélectionner l'option correspondant à la destination choisie
                            $selected = ($destination['destination_id'] == $destination_id) ? 'selected' : '';
                            echo "<option value='".$destination['destination_id']."' ".$selected.">".$destination['destination_name']." - ".$destination['destination_prix']." €</option>";
                        }
                    
                    ?>
                    </select>
            </a>
            <button type="submit" name="form1_submit" class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm w-full sm:w-auto px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">Etape suivante</button>
        </div>
      <br>
</div>
            </form>
            </center>




        <?php elseif ($etape == 2): ?>
            <center>
            <div id="first">
              
              <ol class="space-y-4 w-72">
                  <li>
                  <div class="w-full p-4 text-green-700 border border-green-300 rounded-lg bg-green-50 dark:bg-gray-800 dark:border-green-800 dark:text-green-400" role="alert">
                        <div class="flex items-center justify-between">
                              <span class="sr-only"></span>
                              <h3 class="font-medium">1. Destination</h3>
                              <svg aria-hidden="true" class="w-5 h-5" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M16.707 5.293a1 1 0 010 1.414l-8 8a1 1 0 01-1.414 0l-4-4a1 1 0 011.414-1.414L8 12.586l7.293-7.293a1 1 0 011.414 0z" clip-rule="evenodd"></path></svg>
                            </div>
                      </div>
                  </li>
                  <li>
                  <div class="w-full p-4 text-blue-700 bg-blue-100 border border-blue-300 rounded-lg dark:bg-gray-800 dark:border-blue-800 dark:text-blue-400" role="alert">
                          <div class="flex items-center justify-between">
                              <span class="sr-only"></span>
                              <h3 class="font-medium">2. Choix des dates</h3>
                              <svg aria-hidden="true" class="w-5 h-5" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M12.293 5.293a1 1 0 011.414 0l4 4a1 1 0 010 1.414l-4 4a1 1 0 01-1.414-1.414L14.586 11H3a1 1 0 110-2h11.586l-2.293-2.293a1 1 0 010-1.414z" clip-rule="evenodd"></path></svg>
                          </div>
                      </div>
                  </li>
                  <li>
                  <div class="w-full p-4 text-gray-900 bg-gray-100 border border-gray-300 rounded-lg dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400" role="alert">
                          <div class="flex items-center justify-between">
                              <span class="sr-only"></span>
                              <h3 class="font-medium">3. Nombres de personnes</h3>
                            </div>
                      </div>
                  </li>
                  <li>
                      <div class="w-full p-4 text-gray-900 bg-gray-100 border border-gray-300 rounded-lg dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400" role="alert">
                          <div class="flex items-center justify-between">
                              <span class="sr-only"></span>
                              <h3 class="font-medium">4. Choix du transport</h3>
                          </div>
                      </div>
                  </li>
                  </li>
                      <li>
                      <div class="w-full p-4 text-gray-900 bg-gray-100 border border-gray-300 rounded-lg dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400" role="alert">
                          <div class="flex items-center justify-between">
                              <span class="sr-only"></span>
                              <h3 class="font-medium">5. Choix du logement</h3>
                          </div>
                      </div>
                  </li>
                  <li>
                      <div class="w-full p-4 text-gray-900 bg-gray-100 border border-gray-300 rounded-lg dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400" role="alert">
                          <div class="flex items-center justify-between">
                              <span class="sr-only"></span>
                              <h3 class="font-medium">6. Récapitulatif</h3>
                          </div>
                      </div>
                  </li>
                  <li>
                      <div class="w-full p-4 text-gray-900 bg-gray-100 border border-gray-300 rounded-lg dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400" role="alert">
                          <div class="flex items-center justify-between">
                              <span class="sr-only"></span>
                              <h3 class="font-medium">7. Payement</h3>
                          </div>
                      </div>
                  </li>
              </ol>
              
                          </div>
            <form method="post" class="form">
            <div class="w-full max-w-sm bg-white border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700">
        <br>
        <div class="px-5 pb-5">
            <a href="#">
                <h5 class="text-xl font-semibold tracking-tight text-gray-900 dark:text-white">Date de départ :</h5>
            </a>
            <input type="date" name="date_depart" id="date_depart" class="form-control" placeholder="DATE" required></input>
            <br>
            <a href="#">
                <h5 class="text-xl font-semibold tracking-tight text-gray-900 dark:text-white">Date de retour :</h5>
            </a>
            <input type="date" name="date_retour" id="date_retour" class="form-control" placeholder="DATE" required></input>
            <br><br><br>
            <button type="submit" name="form2_submit" class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm w-full sm:w-auto px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">Etape suivante</button>
        </div>
      <br>
</div>
            </form>
            </center>




            <?php elseif ($etape == 3): ?>
            <center>
            <div id="first">
              
              <ol class="space-y-4 w-72">
                  <li>
                  <div class="w-full p-4 text-green-700 border border-green-300 rounded-lg bg-green-50 dark:bg-gray-800 dark:border-green-800 dark:text-green-400" role="alert">
                        <div class="flex items-center justify-between">
                              <span class="sr-only"></span>
                              <h3 class="font-medium">1. Destination</h3>
                              <svg aria-hidden="true" class="w-5 h-5" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M16.707 5.293a1 1 0 010 1.414l-8 8a1 1 0 01-1.414 0l-4-4a1 1 0 011.414-1.414L8 12.586l7.293-7.293a1 1 0 011.414 0z" clip-rule="evenodd"></path></svg>
                            </div>
                      </div>
                  </li>
                  <li>
                  <div class="w-full p-4 text-green-700 border border-green-300 rounded-lg bg-green-50 dark:bg-gray-800 dark:border-green-800 dark:text-green-400" role="alert">
                        <div class="flex items-center justify-between">
                              <span class="sr-only"></span>
                              <h3 class="font-medium">2. Choix des dates</h3>
                              <svg aria-hidden="true" class="w-5 h-5" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M16.707 5.293a1 1 0 010 1.414l-8 8a1 1 0 01-1.414 0l-4-4a1 1 0 011.414-1.414L8 12.586l7.293-7.293a1 1 0 011.414 0z" clip-rule="evenodd"></path></svg>
                            </div>
                      </div>
                  </li>
                  <li>
                  <div class="w-full p-4 text-blue-700 bg-blue-100 border border-blue-300 rounded-lg dark:bg-gray-800 dark:border-blue-800 dark:text-blue-400" role="alert">
                          <div class="flex items-center justify-between">
                              <span class="sr-only"></span>
                              <h3 class="font-medium">3. Nombres de personnes</h3>
                              <svg aria-hidden="true" class="w-5 h-5" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M12.293 5.293a1 1 0 011.414 0l4 4a1 1 0 010 1.414l-4 4a1 1 0 01-1.414-1.414L14.586 11H3a1 1 0 110-2h11.586l-2.293-2.293a1 1 0 010-1.414z" clip-rule="evenodd"></path></svg>
                          </div>
                      </div>
                  </li>
                  <li>
                      <div class="w-full p-4 text-gray-900 bg-gray-100 border border-gray-300 rounded-lg dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400" role="alert">
                          <div class="flex items-center justify-between">
                              <span class="sr-only"></span>
                              <h3 class="font-medium">4. Choix du transport</h3>
                          </div>
                      </div>
                  </li>
                      <li>
                      <div class="w-full p-4 text-gray-900 bg-gray-100 border border-gray-300 rounded-lg dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400" role="alert">
                          <div class="flex items-center justify-between">
                              <span class="sr-only"></span>
                              <h3 class="font-medium">5. Choix du logement</h3>
                          </div>
                      </div>
                  </li>
                  <li>
                      <div class="w-full p-4 text-gray-900 bg-gray-100 border border-gray-300 rounded-lg dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400" role="alert">
                          <div class="flex items-center justify-between">
                              <span class="sr-only"></span>
                              <h3 class="font-medium">6. Récapitulatif</h3>
                          </div>
                      </div>
                  </li>
                  <li>
                      <div class="w-full p-4 text-gray-900 bg-gray-100 border border-gray-300 rounded-lg dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400" role="alert">
                          <div class="flex items-center justify-between">
                              <span class="sr-only"></span>
                              <h3 class="font-medium">7. Payement</h3>
                          </div>
                      </div>
                  </li>
              </ol>
              
                          </div>
            <form method="post" class="form">
            <div class="w-full max-w-sm bg-white border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700">
        <br>
        <div class="px-5 pb-5">
            <a href="#">
                <h5 class="text-xl font-semibold tracking-tight text-gray-900 dark:text-white">Nombres de Voyageurs :</h5>
                <select name="nombre_voyageurs" id="nombre_voyageurs">
                    <?php for ($i = 1; $i <= 10; $i++): ?>
                        <option value="<?php echo $i; ?>"><?php echo $i; ?></option>
                    <?php endfor; ?>
                </select>
            </a>
            <button type="submit" name="form3_submit" class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm w-full sm:w-auto px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">Etape suivante</button>
</div>
<br>
</div></form>
</center>

<?php elseif ($etape == 4): ?>
            <center>
            <div id="first">
              
              <ol class="space-y-4 w-72">
                  <li>
                  <div class="w-full p-4 text-green-700 border border-green-300 rounded-lg bg-green-50 dark:bg-gray-800 dark:border-green-800 dark:text-green-400" role="alert">
                        <div class="flex items-center justify-between">
                              <span class="sr-only"></span>
                              <h3 class="font-medium">1. Destination</h3>
                              <svg aria-hidden="true" class="w-5 h-5" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M16.707 5.293a1 1 0 010 1.414l-8 8a1 1 0 01-1.414 0l-4-4a1 1 0 011.414-1.414L8 12.586l7.293-7.293a1 1 0 011.414 0z" clip-rule="evenodd"></path></svg>
                            </div>
                      </div>
                  </li>
                  <li>
                  <div class="w-full p-4 text-green-700 border border-green-300 rounded-lg bg-green-50 dark:bg-gray-800 dark:border-green-800 dark:text-green-400" role="alert">
                        <div class="flex items-center justify-between">
                              <span class="sr-only"></span>
                              <h3 class="font-medium">2. Choix des dates</h3>
                              <svg aria-hidden="true" class="w-5 h-5" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M16.707 5.293a1 1 0 010 1.414l-8 8a1 1 0 01-1.414 0l-4-4a1 1 0 011.414-1.414L8 12.586l7.293-7.293a1 1 0 011.414 0z" clip-rule="evenodd"></path></svg>
                            </div>
                      </div>
                  </li>
                  <li>
                  <div class="w-full p-4 text-green-700 border border-green-300 rounded-lg bg-green-50 dark:bg-gray-800 dark:border-green-800 dark:text-green-400" role="alert">
                        <div class="flex items-center justify-between">
                              <span class="sr-only"></span>
                              <h3 class="font-medium">3. Nombres de personnes</h3>
                              <svg aria-hidden="true" class="w-5 h-5" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M16.707 5.293a1 1 0 010 1.414l-8 8a1 1 0 01-1.414 0l-4-4a1 1 0 011.414-1.414L8 12.586l7.293-7.293a1 1 0 011.414 0z" clip-rule="evenodd"></path></svg>
                            </div>
                      </div>
                  </li>
                  <li>
                  <div class="w-full p-4 text-blue-700 bg-blue-100 border border-blue-300 rounded-lg dark:bg-gray-800 dark:border-blue-800 dark:text-blue-400" role="alert">
                          <div class="flex items-center justify-between">
                              <span class="sr-only"></span>
                              <h3 class="font-medium">4. Choix du transport</h3>
                              <svg aria-hidden="true" class="w-5 h-5" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M12.293 5.293a1 1 0 011.414 0l4 4a1 1 0 010 1.414l-4 4a1 1 0 01-1.414-1.414L14.586 11H3a1 1 0 110-2h11.586l-2.293-2.293a1 1 0 010-1.414z" clip-rule="evenodd"></path></svg>
                          </div>
                      </div>
                  </li>
                      <li>
                      <div class="w-full p-4 text-gray-900 bg-gray-100 border border-gray-300 rounded-lg dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400" role="alert">
                          <div class="flex items-center justify-between">
                              <span class="sr-only"></span>
                              <h3 class="font-medium">5. Choix du logement</h3>
                          </div>
                      </div>
                  </li>
                  <li>
                      <div class="w-full p-4 text-gray-900 bg-gray-100 border border-gray-300 rounded-lg dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400" role="alert">
                          <div class="flex items-center justify-between">
                              <span class="sr-only"></span>
                              <h3 class="font-medium">6. Récapitulatif</h3>
                          </div>
                      </div>
                  </li>
                  <li>
                      <div class="w-full p-4 text-gray-900 bg-gray-100 border border-gray-300 rounded-lg dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400" role="alert">
                          <div class="flex items-center justify-between">
                              <span class="sr-only"></span>
                              <h3 class="font-medium">7. Payement</h3>
                          </div>
                      </div>
                  </li>
              </ol>
              
                          </div>
                          <form method="post" class="form">
            <div class="w-full max-w-sm bg-white border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700">
        <br>
        <div class="px-5 pb-5">
            <a href="#">
                <h5 class="text-xl font-semibold tracking-tight text-gray-900 dark:text-white">Moyen de transport :</h5>
                <select name="transport" id="transport">
                    <?php 
                            $transports = $unControleur->getTransports();

                            foreach($transports as $transport) {
                                echo "<option value='".$transport['transport_id']."'>".$transport['transport_name']." - ".$transport['transport_prix']." €</option>";
                            }
                     ?>
                </select>
            </a>
            <button type="submit" name="form4_submit" class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm w-full sm:w-auto px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">Etape suivante</button>
</div>
<br>
</div></form>
</center>
                          <?php elseif ($etape == 5): ?>
            <center>
            <div id="first">
              
              <ol class="space-y-4 w-72">
                  <li>
                  <div class="w-full p-4 text-green-700 border border-green-300 rounded-lg bg-green-50 dark:bg-gray-800 dark:border-green-800 dark:text-green-400" role="alert">
                        <div class="flex items-center justify-between">
                              <span class="sr-only"></span>
                              <h3 class="font-medium">1. Destination</h3>
                              <svg aria-hidden="true" class="w-5 h-5" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M16.707 5.293a1 1 0 010 1.414l-8 8a1 1 0 01-1.414 0l-4-4a1 1 0 011.414-1.414L8 12.586l7.293-7.293a1 1 0 011.414 0z" clip-rule="evenodd"></path></svg>
                            </div>
                      </div>
                  </li>
                  <li>
                  <div class="w-full p-4 text-green-700 border border-green-300 rounded-lg bg-green-50 dark:bg-gray-800 dark:border-green-800 dark:text-green-400" role="alert">
                        <div class="flex items-center justify-between">
                              <span class="sr-only"></span>
                              <h3 class="font-medium">2. Choix des dates</h3>
                              <svg aria-hidden="true" class="w-5 h-5" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M16.707 5.293a1 1 0 010 1.414l-8 8a1 1 0 01-1.414 0l-4-4a1 1 0 011.414-1.414L8 12.586l7.293-7.293a1 1 0 011.414 0z" clip-rule="evenodd"></path></svg>
                            </div>
                      </div>
                  </li>
                  <li>
                  <div class="w-full p-4 text-green-700 border border-green-300 rounded-lg bg-green-50 dark:bg-gray-800 dark:border-green-800 dark:text-green-400" role="alert">
                        <div class="flex items-center justify-between">
                              <span class="sr-only"></span>
                              <h3 class="font-medium">3. Nombres de personnes</h3>
                              <svg aria-hidden="true" class="w-5 h-5" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M16.707 5.293a1 1 0 010 1.414l-8 8a1 1 0 01-1.414 0l-4-4a1 1 0 011.414-1.414L8 12.586l7.293-7.293a1 1 0 011.414 0z" clip-rule="evenodd"></path></svg>
                            </div>
                      </div>
                  </li>
                  <li>
                  <div class="w-full p-4 text-green-700 border border-green-300 rounded-lg bg-green-50 dark:bg-gray-800 dark:border-green-800 dark:text-green-400" role="alert">
                        <div class="flex items-center justify-between">
                              <span class="sr-only"></span>
                              <h3 class="font-medium">3. Choix du transport</h3>
                              <svg aria-hidden="true" class="w-5 h-5" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M16.707 5.293a1 1 0 010 1.414l-8 8a1 1 0 01-1.414 0l-4-4a1 1 0 011.414-1.414L8 12.586l7.293-7.293a1 1 0 011.414 0z" clip-rule="evenodd"></path></svg>
                            </div>
                      </div>
                  </li>
                  <li>
                  <div class="w-full p-4 text-blue-700 bg-blue-100 border border-blue-300 rounded-lg dark:bg-gray-800 dark:border-blue-800 dark:text-blue-400" role="alert">
                          <div class="flex items-center justify-between">
                              <span class="sr-only"></span>
                              <h3 class="font-medium">5. Choix du logement</h3>
                              <svg aria-hidden="true" class="w-5 h-5" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M12.293 5.293a1 1 0 011.414 0l4 4a1 1 0 010 1.414l-4 4a1 1 0 01-1.414-1.414L14.586 11H3a1 1 0 110-2h11.586l-2.293-2.293a1 1 0 010-1.414z" clip-rule="evenodd"></path></svg>
                          </div>
                      </div>
                  </li>
                  <li>
                      <div class="w-full p-4 text-gray-900 bg-gray-100 border border-gray-300 rounded-lg dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400" role="alert">
                          <div class="flex items-center justify-between">
                              <span class="sr-only"></span>
                              <h3 class="font-medium">6. Récapitulatif</h3>
                          </div>
                      </div>
                  </li>
                  <li>
                      <div class="w-full p-4 text-gray-900 bg-gray-100 border border-gray-300 rounded-lg dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400" role="alert">
                          <div class="flex items-center justify-between">
                              <span class="sr-only"></span>
                              <h3 class="font-medium">7. Payement</h3>
                          </div>
                      </div>
                  </li>
              </ol>
              
                          </div>

                          <form method="post" class="form">
            <div class="w-full max-w-sm bg-white border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700">
        <br>
        <div class="px-5 pb-5">
            <a href="#">
                <h5 class="text-xl font-semibold tracking-tight text-gray-900 dark:text-white">Logement :</h5>
                <select name="logement" id="logement">
                    <?php 
                            $logements = $unControleur->getLogements();

                            foreach ($logements as $unLogement) {
                                echo "<option value='".$unLogement['logement_id']."'>".$unLogement['logement_name']." - ".$unLogement['logement_prix']."</option>";
                            }
                     ?>
                </select>
            </a>
            <button type="submit" name="form5_submit" class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm w-full sm:w-auto px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">Etape suivante</button>
</div>
<br>
</div></form>
</center>
                          <?php elseif ($etape == 6): ?>
            <center>
            <div id="first">
              
              <ol class="space-y-4 w-72">
                  <li>
                  <div class="w-full p-4 text-green-700 border border-green-300 rounded-lg bg-green-50 dark:bg-gray-800 dark:border-green-800 dark:text-green-400" role="alert">
                        <div class="flex items-center justify-between">
                              <span class="sr-only"></span>
                              <h3 class="font-medium">1. Destination</h3>
                              <svg aria-hidden="true" class="w-5 h-5" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M16.707 5.293a1 1 0 010 1.414l-8 8a1 1 0 01-1.414 0l-4-4a1 1 0 011.414-1.414L8 12.586l7.293-7.293a1 1 0 011.414 0z" clip-rule="evenodd"></path></svg>
                            </div>
                      </div>
                  </li>
                  <li>
                  <div class="w-full p-4 text-green-700 border border-green-300 rounded-lg bg-green-50 dark:bg-gray-800 dark:border-green-800 dark:text-green-400" role="alert">
                        <div class="flex items-center justify-between">
                              <span class="sr-only"></span>
                              <h3 class="font-medium">2. Choix des dates</h3>
                              <svg aria-hidden="true" class="w-5 h-5" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M16.707 5.293a1 1 0 010 1.414l-8 8a1 1 0 01-1.414 0l-4-4a1 1 0 011.414-1.414L8 12.586l7.293-7.293a1 1 0 011.414 0z" clip-rule="evenodd"></path></svg>
                            </div>
                      </div>
                  </li>
                  <li>
                  <div class="w-full p-4 text-green-700 border border-green-300 rounded-lg bg-green-50 dark:bg-gray-800 dark:border-green-800 dark:text-green-400" role="alert">
                        <div class="flex items-center justify-between">
                              <span class="sr-only"></span>
                              <h3 class="font-medium">3. Nombres de personnes</h3>
                              <svg aria-hidden="true" class="w-5 h-5" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M16.707 5.293a1 1 0 010 1.414l-8 8a1 1 0 01-1.414 0l-4-4a1 1 0 011.414-1.414L8 12.586l7.293-7.293a1 1 0 011.414 0z" clip-rule="evenodd"></path></svg>
                            </div>
                      </div>
                  </li>
                  <li>
                  <div class="w-full p-4 text-green-700 border border-green-300 rounded-lg bg-green-50 dark:bg-gray-800 dark:border-green-800 dark:text-green-400" role="alert">
                        <div class="flex items-center justify-between">
                              <span class="sr-only"></span>
                              <h3 class="font-medium">4. Choix du transport</h3>
                              <svg aria-hidden="true" class="w-5 h-5" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M16.707 5.293a1 1 0 010 1.414l-8 8a1 1 0 01-1.414 0l-4-4a1 1 0 011.414-1.414L8 12.586l7.293-7.293a1 1 0 011.414 0z" clip-rule="evenodd"></path></svg>
                            </div>
                      </div>
                  </li>
                  <li>
                  <div class="w-full p-4 text-green-700 border border-green-300 rounded-lg bg-green-50 dark:bg-gray-800 dark:border-green-800 dark:text-green-400" role="alert">
                        <div class="flex items-center justify-between">
                              <span class="sr-only"></span>
                              <h3 class="font-medium">5. Choix du logement</h3>
                              <svg aria-hidden="true" class="w-5 h-5" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M16.707 5.293a1 1 0 010 1.414l-8 8a1 1 0 01-1.414 0l-4-4a1 1 0 011.414-1.414L8 12.586l7.293-7.293a1 1 0 011.414 0z" clip-rule="evenodd"></path></svg>
                            </div>
                      </div>
                  </li>
                  <li>
                  <div class="w-full p-4 text-blue-700 bg-blue-100 border border-blue-300 rounded-lg dark:bg-gray-800 dark:border-blue-800 dark:text-blue-400" role="alert">
                          <div class="flex items-center justify-between">
                              <span class="sr-only"></span>
                              <h3 class="font-medium">6. Récapitulatif</h3>
                              <svg aria-hidden="true" class="w-5 h-5" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M12.293 5.293a1 1 0 011.414 0l4 4a1 1 0 010 1.414l-4 4a1 1 0 01-1.414-1.414L14.586 11H3a1 1 0 110-2h11.586l-2.293-2.293a1 1 0 010-1.414z" clip-rule="evenodd"></path></svg>
                          </div>
                      </div>
                  </li>
                  <li>
                      <div class="w-full p-4 text-gray-900 bg-gray-100 border border-gray-300 rounded-lg dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400" role="alert">
                          <div class="flex items-center justify-between">
                              <span class="sr-only"></span>
                              <h3 class="font-medium">7. Payement</h3>
                          </div>
                      </div>
                  </li>
              </ol>
              
                          </div>
                          <center>

                            <form method="post" class="form">
                <div class="w-full max-w-md bg-white border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700">
                    <br>
                    <div class="px-5 pb-5">
                        
                        <h5 class="text-xl font-semibold tracking-tight text-gray-900 dark:text-white">Destination choisie :</h5>
                        <p><?php echo $unControleur->getDestinationName(); ?></p>

                        <h5 class="text-xl font-semibold tracking-tight text-gray-900 dark:text-white">Date de départ :</h5>
                        <p><?php echo $_SESSION['date_depart'] ?></p>

                        <h5 class="text-xl font-semibold tracking-tight text-gray-900 dark:text-white">Date de retour :</h5>
                        <p><?php echo $_SESSION['date_retour'] ?></p>

                        <h5 class="text-xl font-semibold tracking-tight text-gray-900 dark:text-white">Nombre de voyageur(s) :</h5>
                        <p><?php echo $_SESSION['nombre_voyageurs'] ?></p>

                        <h5 class="text-xl font-semibold tracking-tight text-gray-900 dark:text-white">Transport choisie :</h5>
                        <p><?php echo $unControleur->getTransportName(); ?></p>

                        <h5 class="text-xl font-semibold tracking-tight text-gray-900 dark:text-white">Logement choisie :</h5>
                        <p><?php echo $unControleur->getLogementName(); ?></p>

                        <h5 class="text-xl font-semibold tracking-tight text-gray-900 dark:text-white">Prix total :</h5>
                        <p><?php 
                        $destination_id = $unControleur->getDestinationPrix();
                        $transport_id = $unControleur->getTransportPrix();
                        $logement_id = $unControleur->getLogementPrix();
                        $nombre_voyageurs = $_SESSION['nombre_voyageurs'];

                        // Date de départ et de retour
                        $date_depart = $_SESSION['date_depart'];
                        $date_retour = $_SESSION['date_retour'];

                        // Conversion des dates en objets DateTime
                        $objet_date_depart = new DateTime($date_depart);
                        $objet_date_retour = new DateTime($date_retour);

                        // Calcul de l'écart en nombre de jours
                        $ecart_jours = $objet_date_depart->diff($objet_date_retour)->format('%a');

                        $prix_1 = $destination_id + $transport_id;
                        $prix_2 = $ecart_jours * $logement_id;
                        $prix = $prix_1 + $prix_2;
                        $prix_total = $prix * $nombre_voyageurs;

                        // echo $prix_total;
                        echo $_SESSION['prix_total'] = $prix_total;
                             ?> 
                        €</p>
                        <br>
                        <button type="submit" name="form6_submit" class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm w-full sm:w-auto px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">Réserver</button>
                    </div>
                </div>
                          </form>
            </center>
</center>


<?php elseif ($etape == 7): ?>
                <center>
                    <?php 
                    // var_dump($_SESSION); 
                    ?>
            <div id="first">
              
              <ol class="space-y-4 w-72">
                  <li>
                  <div class="w-full p-4 text-green-700 border border-green-300 rounded-lg bg-green-50 dark:bg-gray-800 dark:border-green-800 dark:text-green-400" role="alert">
                        <div class="flex items-center justify-between">
                              <span class="sr-only"></span>
                              <h3 class="font-medium">1. Destination</h3>
                              <svg aria-hidden="true" class="w-5 h-5" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M16.707 5.293a1 1 0 010 1.414l-8 8a1 1 0 01-1.414 0l-4-4a1 1 0 011.414-1.414L8 12.586l7.293-7.293a1 1 0 011.414 0z" clip-rule="evenodd"></path></svg>
                            </div>
                      </div>
                  </li>
                  <li>
                  <div class="w-full p-4 text-green-700 border border-green-300 rounded-lg bg-green-50 dark:bg-gray-800 dark:border-green-800 dark:text-green-400" role="alert">
                        <div class="flex items-center justify-between">
                              <span class="sr-only"></span>
                              <h3 class="font-medium">2. Choix des dates</h3>
                              <svg aria-hidden="true" class="w-5 h-5" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M16.707 5.293a1 1 0 010 1.414l-8 8a1 1 0 01-1.414 0l-4-4a1 1 0 011.414-1.414L8 12.586l7.293-7.293a1 1 0 011.414 0z" clip-rule="evenodd"></path></svg>
                            </div>
                      </div>
                  </li>
                  <li>
                  <div class="w-full p-4 text-green-700 border border-green-300 rounded-lg bg-green-50 dark:bg-gray-800 dark:border-green-800 dark:text-green-400" role="alert">
                        <div class="flex items-center justify-between">
                              <span class="sr-only"></span>
                              <h3 class="font-medium">3. Nombres de personnes</h3>
                              <svg aria-hidden="true" class="w-5 h-5" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M16.707 5.293a1 1 0 010 1.414l-8 8a1 1 0 01-1.414 0l-4-4a1 1 0 011.414-1.414L8 12.586l7.293-7.293a1 1 0 011.414 0z" clip-rule="evenodd"></path></svg>
                            </div>
                      </div>
                  </li>
                  <li>
                  <div class="w-full p-4 text-green-700 border border-green-300 rounded-lg bg-green-50 dark:bg-gray-800 dark:border-green-800 dark:text-green-400" role="alert">
                        <div class="flex items-center justify-between">
                              <span class="sr-only"></span>
                              <h3 class="font-medium">4. Choix du transport</h3>
                              <svg aria-hidden="true" class="w-5 h-5" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M16.707 5.293a1 1 0 010 1.414l-8 8a1 1 0 01-1.414 0l-4-4a1 1 0 011.414-1.414L8 12.586l7.293-7.293a1 1 0 011.414 0z" clip-rule="evenodd"></path></svg>
                            </div>
                      </div>
                  </li>
                  <li>
                  <div class="w-full p-4 text-green-700 border border-green-300 rounded-lg bg-green-50 dark:bg-gray-800 dark:border-green-800 dark:text-green-400" role="alert">
                        <div class="flex items-center justify-between">
                              <span class="sr-only"></span>
                              <h3 class="font-medium">5. Choix du logement</h3>
                              <svg aria-hidden="true" class="w-5 h-5" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M16.707 5.293a1 1 0 010 1.414l-8 8a1 1 0 01-1.414 0l-4-4a1 1 0 011.414-1.414L8 12.586l7.293-7.293a1 1 0 011.414 0z" clip-rule="evenodd"></path></svg>
                            </div>
                      </div>
                  </li>
                  <li>
                  <div class="w-full p-4 text-green-700 border border-green-300 rounded-lg bg-green-50 dark:bg-gray-800 dark:border-green-800 dark:text-green-400" role="alert">
                        <div class="flex items-center justify-between">
                              <span class="sr-only"></span>
                              <h3 class="font-medium">6. Récapitulatif</h3>
                              <svg aria-hidden="true" class="w-5 h-5" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M16.707 5.293a1 1 0 010 1.414l-8 8a1 1 0 01-1.414 0l-4-4a1 1 0 011.414-1.414L8 12.586l7.293-7.293a1 1 0 011.414 0z" clip-rule="evenodd"></path></svg>
                            </div>
                      </div>
                  </li>
                  <li>
                  <div class="w-full p-4 text-blue-700 bg-blue-100 border border-blue-300 rounded-lg dark:bg-gray-800 dark:border-blue-800 dark:text-blue-400" role="alert">
                          <div class="flex items-center justify-between">
                              <span class="sr-only"></span>
                              <h3 class="font-medium">7. Payement</h3>
                              <svg aria-hidden="true" class="w-5 h-5" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M12.293 5.293a1 1 0 011.414 0l4 4a1 1 0 010 1.414l-4 4a1 1 0 01-1.414-1.414L14.586 11H3a1 1 0 110-2h11.586l-2.293-2.293a1 1 0 010-1.414z" clip-rule="evenodd"></path></svg>
                          </div>
                      </div>
                  </li>
              </ol>
              
                          </div>

            <center>
               <div class="max-w-sm mx-auto p-6 dark:bg-gray-800 rounded-md shadow-md">
  <h2 class="text-2xl font-medium mb-4 text-white">Travelin secure</h2>
  <form method="POST" action="index.php?page=0">
    <div class="mb-4">
      <label class="block text-white font-bold mb-2" for="card-holder">Titulaire de la carte</label>
      <input class="appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" id="card-holder" type="text" placeholder="Nom et prénom">
    </div>
    <div class="mb-4">
      <label class="block text-white font-bold mb-2" for="card-number">Numéro de carte</label>
      <input class="appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" id="card-number" type="text" placeholder="1234 5678 9012 3456">
    </div>
    <div class="flex mb-4">
      <div class="w-1/2 mr-2">
        <label class="block text-white font-bold mb-2" for="card-expiration">Date d'expiration</label>
        <input class="appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" id="card-expiration" type="text" placeholder="MM/AA">
      </div>
      <div class="w-1/2 ml-2">
        <label class="block text-white font-bold mb-2" for="card-cvc">CCV</label>
        <input class="appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" id="card-cvc" type="text" placeholder="123">
      </div>
    </div>

<br>
    <button class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline" type="submit" name="resa" id="resa">
      Payer
    </button>
  </form>
</div>

        <?php endif ?>


    </div>
</div>
</div>
<?php
        // }
    // } catch (PDOException $e) {
    //     echo "Erreur : " . $e->getMessage();
    // }
// }
?>
</body>
</html>

<?php

// } else {
//     header('Location: index.php?page=6');
// }

?>