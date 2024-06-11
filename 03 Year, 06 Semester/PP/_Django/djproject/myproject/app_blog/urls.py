from django.urls import path
from .views import (ArticleDetail,
ArticleList, ArticleCategoryList)

urlpatterns = [
    path(r'', ArticleList.as_view(), name='articles-list'),
    path(r'category/<slug>', ArticleCategoryList.as_view(), name='articles-category-list'),
    path(r'<year>/<month>/<day>/<slug>', ArticleDetail.as_view(), name='news-detail'),
]