//处理树数组的js

const treeUtils = {

  createTreeNode(pre, node, arr) {
    if (node) {
      for (var i = 0; i < arr.length; i++) {//扫描所有的子节点
        if (arr[i].pid == node.nodeid) {
          node.nodes.push(arr[i]);
          arr[i].isChild = true;
        }
      }
    }
    if ((pre + 1) < arr.length) {
      createTreeNode(pre + 1, arr[pre + 1], arr);
    }//设置第归边界
    return node;
  },

  createTree(arr) {
    createTreeNode(0, arr[0], arr);
    for (var i = 0; i < arr.length; i++) {
      if (arr[i].isChild) {//所有的子节点都存在这个标志
        arr.splice(i, 1);
        i--;
      }
    }
    return arr;
  },
  //递归所有子节点，找到所有层级节点
  getChildNodeIdArr(node) {
    var ts = [];
    if (node.nodes) {
      for (x in node.nodes) {
        ts.push(node.nodes[x].nodeId);
        if (node.nodes[x].nodes) {
          var getNodeDieDai = getChildNodeIdArr(node.nodes[x]);//有第二层，第三层子节点的情况
          for (j in getNodeDieDai) {
            ts.push(getNodeDieDai[j]);
          }
        }
      }
    } else {
      ts.push(node.nodeId);
    }
    return ts;
  },

  setParentNodeCheck(node) {
    var parentNode = $("#tree").treeview("getNode", node.parentId);
    if (parentNode.nodes) {
      var checkedCount = 0;
      for (x in parentNode.nodes) {
        if (parentNode.nodes[x].state.checked) {
          checkedCount++;
        } else {
          break;
        }
      }
      if (checkedCount === parentNode.nodes.length) {
        $("#treeview").treeview("checkNode", parentNode.nodeId);
        setParentNodeCheck(parentNode);
      }
    }
  }
}


module.exports = treeUtils
