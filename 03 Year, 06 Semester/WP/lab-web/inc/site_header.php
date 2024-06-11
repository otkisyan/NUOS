<?php

/**
 * @var string $currentPage
 */
?>

<header class="site-header">
  <?php if ($currentPage === 'home') { ?>
    <h1 class="site-title">
      <a href="index.php">Scriptor</a>
    </h1>
  <?php } else { ?>
    <div class="site-title">
      <a href="index.php">Scriptor</a>
    </div>
  <?php } ?>
  <nav class="site-nav">
    <ul>
      <li <?php if ('home' === $currentPage) { ?> class="current" <?php } ?>><a href="index.php">Home</a></li>
      <li <?php if ('about' === $currentPage) { ?> class="current" <?php } ?>><a href="about.php">About</a></li>
    </ul>
  </nav>
</header>