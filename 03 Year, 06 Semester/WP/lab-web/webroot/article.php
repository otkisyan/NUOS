<?php require_once '../inc/functions.php' ?>
<?php
$currentPage = "article";

$articleId = $_GET['id'] ?? null;

if (null === $articleId) {
  http_response_code(400);
  exit();
}

$articleId = (int) $articleId;
$article = getArticle($articleId);
if (null === $article) {
  http_response_code(404);
  exit();
} else {
  $siteTitle = $article['title'];
}

$comments = getComments($articleId);

$errors = [];
$action = $_POST['action'] ?? null;
if ($action === 'add-comment') {
  $name = trim((string) ($_POST['name'] ?? null));
  if ('' === $name) {
    $errors['name'] = 'Name is required';
  } elseif (mb_strlen($name) > 50) {
    $errors['name'] = 'Length can not be more than 50 characters';
  }

  $rate = (int) ($_POST['rate'] ?? null);
  if (0 === $rate) {
    $errors['rate'] = 'Rate is required';
  } elseif ($rate < 1 || $rate > 5) {
    $errors['rate'] = 'Invalid value for rate field';
  }

  $text = trim((string) ($_POST['text'] ?? null));
  if ('' === $text) {
    $errors['text'] = 'Comment text is required';
  } elseif (mb_strlen($text) > 200) {
    $errors['text'] =  'Length can not be more than 200 characters';
  }

  $isAjax = strtolower($_SERVER['HTTP_X_REQUESTED_WITH'] ?? '') == 'xmlhttprequest';

  if (0 === count($errors)) {
    $comment = [
      'article_id' => $articleId,
      'author' => $name,
      'rate' => $rate,
      'content' => $text
    ];
    newComment($comment);
    if ($isAjax) {
      $comments = getComments($articleId);
      $_POST = [];
      require '../inc/article-comment-form.php';
      require '../inc/article-comment-list.php';
    } else {
      $url = 'article.php?id=' . $articleId;
      header("Location: {$url}");
    }
    exit();
  } else if ($isAjax) {
    require '../inc/article-comment-form.php';
    exit();
  }
}
?>

<!DOCTYPE html>
<html lang="en">
<?php require '../inc/head.php' ?>

<body>
  <div class="container">
    <?php require '../inc/site_header.php' ?>
    <main>
      <div class="main-content">
        <?php require '../inc/article.php' ?>
        <div class="article-comment">
          <h2>Comments</h2>
          <?php require '../inc/article-comment-form.php' ?>
          <?php require '../inc/article-comment-list.php' ?>
        </div>
      </div>
    </main>
    <?php require '../inc/footer.php' ?>
  </div>

  <script>
    jQuery(function() {
      var $scope = jQuery('.article-comment');
      var $form = $scope.find('form');

      $form.on("submit", function() {
        var formData = $form.serialize();
        var url = $form.attr('action');
        jQuery.post(url, formData, function(response) {
          var $commentList = $(response).filter('.article-comment-list');
          $form.empty().append($(response).filter('form').children());
          if ($commentList.length > 0) {
            $scope.find('.article-comment-list').replaceWith($commentList);
          };
        });
        return false;
      });
    });
  </script>
</body>

</html>