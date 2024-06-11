<?php require_once '../inc/functions.php' ?>
<?php
$currentPage = "home";
$siteTitle = "Home Page";
$articles = getArticles()
?>

<!DOCTYPE html>
<html lang="en">
<?php require '../inc/head.php' ?>

<body>
  <div class="container">
    <?php require '../inc/site_header.php' ?>
    <div class="site-description">
      <p>Scriptor is a minimal, clean, modern & responsive Jekyll theme for writers</p>
    </div>
    <main>

      <div class="main-content">
        <?php foreach ($articles as $article) { ?>
          <?php require '../inc/article.php' ?>
        <?php } ?>
      </div>
    </main>
    <?php require '../inc/footer.php' ?>
  </div>
</body>