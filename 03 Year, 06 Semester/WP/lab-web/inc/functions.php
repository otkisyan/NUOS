<?php
$today = date('Y-m-d');
ini_set('display_errors', '1');
ini_set('display_startup_errors', '1');
error_reporting(E_ALL);

function getDbConnection(): PDO
{
    static $connection;
    if ($connection !== null) {
        return $connection;
    }
    $username = 'root';
    $password = 'root';
    $host = '127.0.0.1';
    $dbname = 'web';
    try {
        $connection = new PDO("mysql:host=$host;dbname=$dbname", $username, $password);
    } catch (PDOException $e) {
        echo 'Database connection error';
        http_response_code(500);
        exit();
    }

    return $connection;
}

function getArticles(): array
{
    $pdo = getDbConnection();
    $statement = $pdo->query('
        SELECT articles.*, COUNT(comments.id) AS comments_count, AVG(comments.rate) AS avg_rate
        FROM articles
        LEFT JOIN comments ON articles.id = comments.article_id
        GROUP BY articles.id
    ', PDO::FETCH_ASSOC);
    $articles = $statement->fetchAll();

    foreach ($articles as &$article) {
        $article['id'] = (int)$article['id'];
        $article['comments_count'] = (int)$article['comments_count'];
        $article['avg_rate'] = (float)$article['avg_rate'];
    }
    unset($comment);

    return $articles;
}

function getArticle(int $id): ?array
{
    $pdo = getDbConnection();
    $statement = $pdo->prepare('
    SELECT articles.*, COUNT(comments.id) AS comments_count, AVG(comments.rate) AS avg_rate
    FROM articles
    LEFT JOIN comments ON articles.id = comments.article_id
    WHERE articles.id = ?
    GROUP BY articles.id
    ');
    $statement->execute([$id]);
    $article = $statement->fetch(PDO::FETCH_ASSOC);
    if (false == $article) {
        return null;
    }
    $article['id'] = (int)$article['id'];
    $article['comments_count'] = (int)$article['comments_count'];
    $article['avg_rate'] = (float)$article['avg_rate'];

    return $article;
}

function newComment($comment): void
{
    $pdo = getDbConnection();
    $query = 'INSERT INTO comments (article_id, author, rate, content, created) 
    VALUES (:article_id, :author, :rate, :content, :created)';
    $statement = $pdo->prepare($query);
    $data = [
        'article_id' => $comment['article_id'],
        'author' => $comment['author'],
        'rate' => $comment['rate'],
        'content' => $comment['content'],
        'created' => date('Y-m-d H:i:s')
    ];
    $result = $statement->execute($data);
    if (false === $result) {
        http_response_code(500);
        exit();
    }
}

function getComments(int $articleId): array
{
    $pdo = getDbConnection();
    $query = 'SELECT * FROM comments WHERE comments.article_id = ?';
    $statement = $pdo->prepare($query);
    $statement->execute([$articleId]);
    $comments = $statement->fetchAll(PDO::FETCH_ASSOC);
    foreach ($comments as &$comment) {
        $comment['id'] = (int)$comment['id'];
    }
    unset($comment);

    return $comments;
}
