<?php

/**
 * @var array $errors
 */
?>

<form action="" method="post" id="add-comment-form">
    <input type="hidden" name="action" value="add-comment">
    <div class="input text">
        <label>
            Name <br>
            <input type="text" name="name" value="<?= htmlspecialchars($_POST['name'] ?? ''); ?>">
        </label>
        <?php if (array_key_exists('name', $errors)) { ?>
            <div class="error"><?= $errors['name']; ?></div>
        <?php } ?>
    </div>
    <div class="input select">
        <label>
            Rate <br>
            <select name="rate">
                <option value="">Choose</option>
                <?php for ($i = 1; $i <= 5; $i++) { ?>
                    <option value="<?= $i; ?> " <?= $i === (int) ($_POST['rate'] ?? null) ? 'selected' : '' ?>><?= $i; ?></option>
                <?php } ?>
            </select>
        </label>
        <?php if (array_key_exists('rate', $errors)) { ?>
            <div class="error"><?= $errors['rate']; ?> </div>
        <?php } ?>
    </div>
    <div class="input textarea">
        <label>Comment <br>
            <textarea name="text"><?= htmlspecialchars($_POST['text'] ?? ''); ?></textarea>
        </label>
        <?php if (array_key_exists('text', $errors)) { ?>
            <div class="error"><?= $errors['text']; ?> </div>
        <?php } ?>
    </div>
    <button type="submit">
        Submit
    </button>
</form>