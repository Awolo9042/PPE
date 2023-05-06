<section class="card-body">
  <h1 class="mb-4 text-3xl font-extrabold text-gray-900 dark:text-white md:text-5xl lg:text-6xl">
    <span class="text-transparent bg-clip-text bg-gradient-to-r from-cyan-500 to-blue-800">Destinations</span>
  </h1>

  <?php
  // var_dump($_SESSION['id_client']);
  // var_dump($_SESSION['user_id']);
  ?>

  <br>

  <div class="row">
    <?php 
      $counter = 0;
      $destinations = $unControleur->getDestinations();
      foreach ($destinations as $destination) { 
    ?>
    <div class="col-md-6">
      <div class="global mb-4 px-1">
        <div class="max-w-lg h-100px bg-white border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700 card d-flex flex-column justify-content-center">
          <a href="#">
            <img class="rounded-t-lg w-25" src="images/<?php echo $destination['destination_img']; ?>" alt="" />
          </a>
          <br>
          <div class="card-footer">
          <a href="index.php?page=1&destination_id=<?php echo $destination['destination_id']; ?>" class="inline-flex items-center px-3 py-2 text-xl font-medium text-center text-white bg-blue-700 rounded-lg hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">
            <?php echo $destination['destination_name']; ?>
          </a>
          </div>
        </div>
      </div>
    </div>
    <?php 
        $counter++;
        if($counter%3 == 0){ //si c'est la fin d'une ligne
          echo "</div><div class='row'>";
        }
      } 
    ?>
  </div>
</section>


<style>
  .h-100px {
    height: 375px;
  }
</style>

</body>
</html>
