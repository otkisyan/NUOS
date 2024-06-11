from django.contrib import admin
from .models import Article, ArticleImage, Category
from .forms import ArticleImageForm
from django.shortcuts import get_object_or_404

class CategoryAdmin(admin.ModelAdmin):
    list_display = ('category',)
    prepopulated_fields = {"slug": ["category"]}
    fieldsets = [
        (
            None,
            {
                "fields": ['category'],
            },
        ),
        (
            "Додатково",
            {
                "classes": ["collapse"],
                "fields": ["slug"]
            }
        )
    ]
admin.site.register(Category, CategoryAdmin)

class ArticleImageInline(admin.TabularInline):
    model = ArticleImage
    form = ArticleImageForm
    extra = 0
    
    fieldsets = [
        (
            None,
            {
                "fields": ["title", "image"]
            }
        )
    ]
    
class ArticleAdmin(admin.ModelAdmin):
    list_display = ('title', 'pub_date', 'slug', 'main_page')
    inlines = [
        ArticleImageInline
    ]
    multiupload_form = True
    multiupload_list = False
    prepopulated_fields = {"slug": ["title"]}
    fieldsets = [
        (
            None,
            {
                "fields": ["pub_date", "title", "description", "main_page", "category"]
            }
        ),
        (
            "Додатково",
            {
                "classes": ["collapse"],
                "fields": ["slug"]
            }
        )
    ]
    
    def delete_file(self, pk, request):
        obj = get_object_or_404(ArticleImage, pk=pk)
        return obj.delete()
    
admin.site.register(Article, ArticleAdmin)