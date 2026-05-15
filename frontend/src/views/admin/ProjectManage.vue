<template>
  <div class="project-manage">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>项目管理</span>
          <el-button type="primary" @click="handleAdd">添加项目</el-button>
        </div>
      </template>
      <el-table :data="projects" stripe>
        <el-table-column prop="name" label="项目名称" />
        <el-table-column prop="description" label="描述" show-overflow-tooltip />
        <el-table-column prop="techStack" label="技术栈" show-overflow-tooltip />
        <el-table-column prop="sortOrder" label="排序" width="80" />
        <el-table-column label="操作" width="200">
          <template #default="{ row }">
            <el-button size="small" @click="handleEdit(row)">编辑</el-button>
            <el-button size="small" type="danger" @click="handleDelete(row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <el-dialog v-model="dialogVisible" :title="isEdit ? '编辑项目' : '添加项目'" width="600px">
      <el-form :model="form" label-width="100px">
        <el-form-item label="项目名称" required>
          <el-input v-model="form.name" placeholder="请输入项目名称" />
        </el-form-item>
        <el-form-item label="描述">
          <el-input v-model="form.description" type="textarea" :rows="3" placeholder="请输入项目描述" />
        </el-form-item>
        <el-form-item label="技术栈">
          <el-input v-model="form.techStack" placeholder="多个技术用逗号分隔" />
        </el-form-item>
        <el-form-item label="GitHub地址">
          <el-input v-model="form.githubUrl" placeholder="请输入GitHub地址" />
        </el-form-item>
        <el-form-item label="演示地址">
          <el-input v-model="form.demoUrl" placeholder="请输入演示地址" />
        </el-form-item>
        <el-form-item label="排序">
          <el-input-number v-model="form.sortOrder" :min="0" />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="handleSubmit" :loading="loading">确定</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { getProjects, addProject, updateProject, deleteProject } from '../../api/project'
import { ElMessage, ElMessageBox } from 'element-plus'

const projects = ref([])
const dialogVisible = ref(false)
const isEdit = ref(false)
const loading = ref(false)
const form = ref({
  id: null,
  name: '',
  description: '',
  techStack: '',
  githubUrl: '',
  demoUrl: '',
  sortOrder: 0
})

const loadData = async () => {
  try {
    const res = await getProjects()
    projects.value = res.data || []
  } catch (e) {
    console.error('加载数据失败', e)
  }
}

const handleAdd = () => {
  isEdit.value = false
  form.value = { id: null, name: '', description: '', techStack: '', githubUrl: '', demoUrl: '', sortOrder: 0 }
  dialogVisible.value = true
}

const handleEdit = (row) => {
  isEdit.value = true
  form.value = { ...row }
  dialogVisible.value = true
}

const handleDelete = async (id) => {
  try {
    await ElMessageBox.confirm('确定要删除吗？', '提示', { type: 'warning' })
    await deleteProject(id)
    ElMessage.success('删除成功')
    loadData()
  } catch (e) {
    if (e !== 'cancel') console.error('删除失败', e)
  }
}

const handleSubmit = async () => {
  if (!form.value.name) {
    ElMessage.warning('请输入项目名称')
    return
  }
  try {
    loading.value = true
    if (isEdit.value) {
      await updateProject(form.value.id, form.value)
    } else {
      await addProject(form.value)
    }
    ElMessage.success(isEdit.value ? '更新成功' : '添加成功')
    dialogVisible.value = false
    loadData()
  } catch (e) {
    console.error('操作失败', e)
  } finally {
    loading.value = false
  }
}

onMounted(loadData)
</script>

<style scoped>
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
</style>
