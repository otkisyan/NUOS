from django.test import TestCase
from django.urls import reverse, resolve
from .views import HomePageView, ArticleList, ArticleCategoryList, ArticleDetail

# Create your tests here.

class HomeTests(TestCase):
    
    def test_home_200_status_code(self):
        url = reverse('home')
        response = self.client.get(url)
        self.assertEqual(response.status_code, 200)
        
    def test_home_url_resolves_home_view(self):
        view = resolve('/')
        self.assertEqual(view.func.view_class, HomePageView)
         
    def test_category_200_status_code(self):
        url = reverse('articles-category-list', args=('name',))
        response = self.client.get(url)
        self.assertEqual(response.status_code, 200)
        
    def test_category_resolves_article_category_list_view(self):
        view = resolve('/articles/category/name')
        self.assertEqual(view.func.view_class, ArticleCategoryList)
        
    def test_articles_200_status_code(self):
        url = reverse('articles-list')
        response = self.client.get(url)
        self.assertEqual(response.status_code, 200)
        
    def test_articles_url_resolves_article_list_view(self):
        view = resolve('/articles/')
        self.assertEqual(view.func.view_class, ArticleList)
        
    def test_article_detail_resolves_article__detail_view(self):
        view = resolve('/articles/2024/04/17/name')
        self.assertEqual(view.func.view_class, ArticleDetail)
    