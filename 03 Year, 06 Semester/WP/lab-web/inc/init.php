<?php
require_once "functions.php";
$pdo = getDbConnection();
$statement = $pdo->prepare("INSERT INTO articles SET title=?, content=?, author=?, image=?, created=?");

$articles = [
    [
        'id' => 1, 'title' => 'Mountains',
        'content' => '<p> Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris ac libero vel ante faucibus placerat quis ac lorem. Nullam tempus egestas sem, a consectetur ante pharetra non. Proin eu velit metus. Sed pulvinar ante eget mi consequat, at tristique nunc interdum. </p>',
        'author' => 'Dennis Brooks', 'image' => 'mountain.jpg', 'created' => '2024-03-21'
    ],
    [
        'id' => 2, 'title' => 'Road', 'content' => '<p> Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris ac libero vel ante faucibus placerat quis ac lorem. Nullam tempus egestas sem, a consectetur ante pharetra non. Proin eu velit metus. Sed pulvinar ante eget mi consequat, at tristique nunc interdum. </p>',
        'author' => 'Dennis Brooks', 'image' => 'road.jpg', 'created' => '2024-03-14'
    ],
];
$commentsQuery = 'CREATE TABLE `comments` (
    `id` INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `article_id` INTEGER NOT NULL,
    `author` TEXT NOT NULL,
    `rate` INTEGER NOT NULL,
    `content` TEXT NOT NULL,
    `created` date NOT NULL
    )';

$result = $pdo->exec($commentsQuery);
if ($result === false) {
    http_response_code(500);
    exit();
}

$articlesQuery =
    'CREATE TABLE `articles` (
  `id` INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `content` text DEFAULT NULL,
  `author` varchar(255) NOT NULL,
  `image` varchar(255) NOT NULL,
  `created` date NOT NULL
)';

$result = $pdo->exec($articlesQuery);
if ($result === false) {
    http_response_code(500);
    exit();
}

foreach ($articles as $article) {
    $result = $statement->execute([
        $article['title'], $article['content'], $article['author'],
        $article['image'], $article['created']
    ]);
    if ($result === false) {
        http_response_code(500);
        exit();
    }
}
