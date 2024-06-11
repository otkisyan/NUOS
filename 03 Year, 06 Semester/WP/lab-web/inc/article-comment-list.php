<?php

/**
 * @var array $comments
 */
?>

<div class="article-comment-list">
    <?php if (count($comments) > 0) { ?>
        <ul class="comment-list">
            <?php foreach (array_reverse($comments) as $comment) { ?>
                <li class="comment">
                    <span class="comment-author"><?= htmlspecialchars($comment['author']); ?></span>
                    <span class="comment-created"> <time class="post-date" date="<?= $comment['created'] ?>">
                            <?= $comment['created'] === $today ? 'Today' : date('F j, Y', strtotime($article['created'])) ?></time> </span>
                    <div class="comment-rate">Rate: <?= $comment['rate']; ?></div>
                    <div class="comment-content"><?= nl2br(htmlspecialchars($comment['content'])); ?></div>
                </li>
            <?php } ?>
        </ul>
    <?php } else { ?>
        <p>No comments yet.</p>
    <?php } ?>
</div>