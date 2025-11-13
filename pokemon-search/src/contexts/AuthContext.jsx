import { createContext, useState, useContext, useEffect } from "react";

const AuthContext = createContext();

export const useAuth = () => useContext(AuthContext);

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(null);
  const [isAuthenticated, setIsAuthenticated] = useState(false);

  // Load user from localStorage on mount
  useEffect(() => {
    const storedUser = localStorage.getItem("user");
    if (storedUser) {
      setUser(JSON.parse(storedUser));
      setIsAuthenticated(true);
    }
  }, []);

  const login = async (username, password) => {
    try {
      const response = await fetch("http://localhost:8080/api/user/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ username, password }),
      });

      const data = await response.json();

      if (data.success) {
        setUser(data.user);
        setIsAuthenticated(true);
        localStorage.setItem("user", JSON.stringify(data.user));
        return { success: true, message: data.message };
      } else {
        return { success: false, message: data.message };
      }
    } catch (error) {
      console.error("Login error:", error);
      return { success: false, message: "Network error. Please try again." };
    }
  };

  const register = async (username, email, password, teamName) => {
    try {
      const response = await fetch("http://localhost:8080/api/user/register", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ username, email, password, teamName }),
      });

      if (response.ok) {
        const userData = await response.json();
        
        return await login(username, password);
      } else {
        return { success: false, message: "Username already exists" };
      }
    } catch (error) {
      console.error("Registration error:", error);
      return { success: false, message: "Network error. Please try again." };
    }
  };

  const logout = () => {
    setUser(null);
    setIsAuthenticated(false);
    localStorage.removeItem("user");
  };

  const updateProfile = async (id, updates) => {
    try {
      const response = await fetch(`http://localhost:8080/api/user/${id}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(updates),
      });

      if (!response.ok) {
        return { success: false, message: `Update failed (${response.status})` };
      }

      const updatedUser = await response.json();
      // Don't store password
      const change = { ...updatedUser, password: undefined };
      setUser(change);
      localStorage.setItem("user", JSON.stringify(change));
      return { success: true };
    } catch (err) {
      console.error("Profile update error", err);
      return { success: false, message: "Network error updating profile" };
    }
  };

  const deleteAccount = async (id) => {
    try {
      const response = await fetch(`http://localhost:8080/api/user/${id}`, {
        method: "DELETE",
      });
      if (!response.ok) {
        return { success: false, message: `Delete failed (${response.status})` };
      }
      logout();
      return { success: true };
    } catch (err) {
      console.error("Delete account error", err);
      return { success: false, message: "Network error deleting account" };
    }
  };

  const value = {
    user,
    isAuthenticated,
    login,
    register,
    logout,
    updateProfile,
    deleteAccount,
  };

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
};
