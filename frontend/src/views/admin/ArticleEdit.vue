<template>
  <div class="article-edit">
    <el-card>
      <template #header>{{ isEdit ? '编辑文章' : '写文章' }}</template>
      <el-form :model="form" label-width="100px">
        <el-form-item label="标题" required>
          <el-input v-model="form.title" placeholder="请输入文章标题" />
        </el-form-item>
        <el-form-item label="分类">
          <el-select v-model="form.categoryId" placeholder="请选择分类" clearable>
            <el-option v-for="cat in categories" :key="cat.id" :label="cat.name" :value="cat.id" />
          </el-select>
        </el-form-item>
        <el-form-item label="摘要">
          <el-input v-model="form.summary" type="textarea" :rows="2" placeholder="请输入文章摘要" />
        </el-form-item>
        <el-form-item label="内容">
          <div class="editor-container">
            <MdEditor v-model="form.content" :theme="'light'" style="height: 500px" />
          </div>
        </el-form-item>
        <el-form-item label="发布状态">
          <el-switch v-model="form.isPublished" active-text="发布" inactive-text="草稿" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleSubmit" :loading="loading">保存</el-button>
          <el-button @click="$router.back()">取消</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { MdEditor } from 'md-editor-v3'
import 'md-editor-v3/lib/style.css'
import { getArticle, addArticle, updateArticle } from '../../api/article'
import { getCategories } from '../../api/category'
import { ElMessage } from 'element-plus'

const route = useRoute()
const router = useRouter()

const isEdit = computed(() => !!route.params.id)
const loading = ref(false)
const categories = ref([])

const form = ref({
  id: null,
  title: '',
  categoryId: null,
  summary: '',
  content: '',
  isPublished: false
})

onMounted(async () => {
  try {
    const catRes = await getCategories()
    categories.value = catRes.data || []

    if (isEdit.value) {
      const res = await getArticle(route.params.id)
      form.value = res.data
    }
  } catch (e) {
    console.error('加载数据失败', e)
  }
})

const handleSubmit = async () => {
  if (!form.value.title) {
    ElMessage.warning('请输入标题')
    return
  }
  try {
    loading.value = true
    if (isEdit.value) {
      await updateArticle(form.value.id, form.value)
    } else {
      await addArticle(form.value)
    }
    ElMessage.success('保存成功')
    router.push('/admin/articles')
  } catch (e) {
    console.error('保存失败', e)
  } finally {
    loading.value = false
  }
}
</script>

<style scoped>
.editor-container {
  width: 100%;
}
</style>
