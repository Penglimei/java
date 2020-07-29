package com.plm.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *  二叉树的先序、中序、后序遍历 ---- 非递归
 */
public class traversalTree {

    /**
     *  先序遍历
     *
     * @param root
     * @return  1 2 4 7 3 5 6 8
     */
    public static List<Integer> preOrder(TreeNode root){

        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        while(root!=null || !stack.isEmpty()){
            while(root != null){
                res.add(root.val);
                stack.push(root);
                root = root.left;
            }
            TreeNode temp = stack.pop();
            root = temp.right;
        }

        return res;
    }

    /**
     *  中序遍历
     * @param root
     * @return  4 7 2 1 5 3 8 6
     */
    public static List<Integer> inOrder(TreeNode root){
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        while (root!=null || !stack.isEmpty()){
            while (root != null){
                stack.push(root);
                root = root.left;
            }
            TreeNode temp = stack.pop();
            res.add(temp.val);
            root = temp.right;
        }

        return res;

    }

    /**
     *  后序遍历
     * @param root
     * @return  7 4 2 5 8 6 3 1
     */
    public static List<Integer> postOrder(TreeNode root){
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        // 记录上一节点
        TreeNode pre = null;

        while (cur!=null || !stack.isEmpty()){
            // 当前节点的左边是否为 null
            while (cur != null){
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.peek();
            // 当前节点的右边是否为null 或者 是否已经从孩子节点返回到父节点
            if(cur.right==null || cur.right==pre){
                res.add(cur.val);
                stack.pop();
                pre = cur;
                cur = null;
            }else {
                cur = cur.right;
            }
        }

        return res;

    }
}

/**
 *  二叉树的先序、中序、后序遍历 ---- 递归
 */
class traversalTree2{
    /**
     *  先序遍历
     *
     * @param root
     * @return  1 2 4 7 3 5 6 8
     */
    public static List<Integer> res = new ArrayList<>();
    public static List<Integer> preOrder(TreeNode root){
        if (root == null){
            return null;
        }
        res.add(root.val);
        preOrder(root.left);
        preOrder(root.right);
        return res;
    }

    /**
     *  中序遍历
     * @param root
     * @return  4 7 2 1 5 3 8 6
     */
    public static List<Integer> res1 = new ArrayList<>();
    public static List<Integer> inOrder(TreeNode root){
        if(root == null){
            return null;
        }
        inOrder(root.left);
        res1.add(root.val);
        inOrder(root.right);
        return res1;
    }

    /**
     *  后序遍历
     * @param root
     * @return  7 4 2 5 8 6 3 1
     */
    public static List<Integer> res2 = new ArrayList<>();
    public static List<Integer> postOrder(TreeNode root){

        if(root == null){
            return null;
        }
        postOrder(root.left);
        postOrder(root.right);
        res2.add(root.val);
        return res2;
    }
}

class testTraversalTree {

    // 构建树
    public static TreeNode buildTree(){
        TreeNode root = new TreeNode(1);
        TreeNode a = new TreeNode(2);
        TreeNode b = new TreeNode(3);
        TreeNode c = new TreeNode(4);
        TreeNode d = new TreeNode(5);
        TreeNode e = new TreeNode(6);
        TreeNode f = new TreeNode(7);
        TreeNode g = new TreeNode(8);

        root.left = a;
        root.right = b;
        a.left = c;
        c.right = f;
        b.left = d;
        b.right = e;
        e.left = g;

        return root;
    }


    public static void main(String[] args) {
        TreeNode root = buildTree();

        System.out.println("======非递归=====");

        List<Integer> res = traversalTree.preOrder(root);
        System.out.println("先序遍历：");
        for(Integer in : res){
            System.out.print(in + " ");
        }
        System.out.println();
        System.out.println("==============");

        List<Integer> res2 = traversalTree.inOrder(root);
        System.out.println("中序遍历：");
        for(Integer in : res2){
            System.out.print(in + " ");
        }
        System.out.println();
        System.out.println("==============");

        List<Integer> res3 = traversalTree.postOrder(root);
        System.out.println("后序遍历：");
        for(Integer in : res3){
            System.out.print(in + " ");
        }
        System.out.println();
        System.out.println();


        System.out.println("======递归=====");

        List<Integer> res1 = traversalTree2.preOrder(root);
        System.out.println("先序遍历：");
        for(Integer in : res1){
            System.out.print(in + " ");
        }
        System.out.println();
        System.out.println("==============");

        List<Integer> res22 = traversalTree2.inOrder(root);
        System.out.println("中序遍历：");
        for(Integer in : res22){
            System.out.print(in + " ");
        }
        System.out.println();
        System.out.println("==============");

        List<Integer> res33 = traversalTree2.postOrder(root);
        System.out.println("后序遍历：");
        for(Integer in : res33){
            System.out.print(in + " ");
        }
        System.out.println();
        System.out.println("==============");
    }

}