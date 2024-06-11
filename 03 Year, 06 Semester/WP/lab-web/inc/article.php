<?php

/**
 * @var string $article
 * @var string $today
 * @var string $currentPage
 */
?>

<article class="post">
    <header class="post-header">
        <time class="post-date" date="<?= $article['created'] ?>">
            <?= $article['created'] === $today ? 'Today' : date('F j, Y', strtotime($article['created'])) ?></time>
        <?php $titleTag = ($currentPage === "home") ? 'h2' : 'h1' ?>
        <<?= $titleTag; ?> class="post-title">
            <a href="article.php?id=<?= $article['id']; ?>"><?= $article['title'] ?></a>
        </<?= $titleTag; ?>>
        <div class="post-meta">
            <span class="post-author">Posted by <a href="#"><?= $article['author'] ?></a></span>
            <span class="post-topics">in <a href="#">Getting Started</a></span>
            <br>
            <span class="comments">Comments:
                <a href="article.php?id=<?= $article['id']; ?>"><?= $article['comments_count'] ?></a></span>
            <span class="comments">Average Rate:
                <a href="article.php?id=<?= $article['id']; ?>">
                    <?= round($article['avg_rate'], 1) ?>
                </a>
            </span>
        </div>
        <img class="post-image" src="images/<?= $article['image'] ?>" />
    </header>
    <div class="post-content">
        <?= $article['content'] ?>
    </div>
    <?php if ($currentPage === "home") { ?>
        <footer class="read-more">
            <a href="article.php?id=<?= $article['id']; ?>">Continue reading →</a>
        </footer>
    <?php } ?>
</article>