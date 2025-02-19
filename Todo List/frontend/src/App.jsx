import { useEffect, useState } from "react";
import axios from "axios";

function App() {
  const [todos, setTodos] = useState([]);
  const [newTodo, setNewTodo] = useState("");
  const [statusFilter, setStatusFilter] = useState(null);
  const [tagFilter, setTagFilter] = useState("");

  useEffect(() => {
    fetchTodos();
  }, [statusFilter, tagFilter]);

  const fetchTodos = async () => {
    let url = "http://localhost:8080/todos?page=0&size=10";
    if (statusFilter !== null) url += `&status=${statusFilter}`;
    if (tagFilter) url += `&tag=${tagFilter}`;
    const response = await axios.get(url);
    setTodos(response.data.content);
  };

  const addTodo = async () => {
    if (!newTodo.trim()) return;
    const response = await axios.post("http://localhost:8080/todos", {
      description: newTodo,
      status: false,
    });
    setTodos([...todos, response.data]);
    setNewTodo("");
  };

  const toggleStatus = async (id, status) => {
    const response = await axios.put(`http://localhost:8080/todos/${id}`, {
      status: !status,
    });
    setTodos(todos.map(todo => (todo.id === id ? response.data : todo)));
  };

  const deleteTodo = async (id) => {
    await axios.delete(`http://localhost:8080/todos/${id}`);
    setTodos(todos.filter(todo => todo.id !== id));
  };

  return (
    <div className="p-6 max-w-2xl mx-auto">
      <h1 className="text-2xl font-bold mb-4">Todo List</h1>
      <div className="flex gap-2 mb-4">
        <input
          type="text"
          className="border p-2 flex-1"
          placeholder="New todo..."
          value={newTodo}
          onChange={(e) => setNewTodo(e.target.value)}
        />
        <button className="bg-blue-500 text-white px-4 py-2" onClick={addTodo}>Add</button>
      </div>
      <div className="mb-4 flex gap-2">
        <button className={`px-4 py-2 ${statusFilter === true ? 'bg-green-500' : 'bg-gray-300'}`} onClick={() => setStatusFilter(true)}>Completed</button>
        <button className={`px-4 py-2 ${statusFilter === false ? 'bg-red-500' : 'bg-gray-300'}`} onClick={() => setStatusFilter(false)}>Pending</button>
        <button className="px-4 py-2 bg-gray-300" onClick={() => setStatusFilter(null)}>All</button>
      </div>
      <ul>
        {todos.map((todo) => (
          <li key={todo.id} className="flex justify-between items-center p-2 border-b">
            <span className={todo.status ? "line-through" : ""}>{todo.description}</span>
            <div>
              <button className="mr-2 text-sm text-blue-600" onClick={() => toggleStatus(todo.id, todo.status)}>
                {todo.status ? "Mark Pending" : "Mark Complete"}
              </button>
              <button className="text-sm text-red-600" onClick={() => deleteTodo(todo.id)}>Delete</button>
            </div>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default App
