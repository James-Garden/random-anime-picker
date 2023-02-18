<#macro submitButton
  class="submit-button"
  id="submitButton"
  preventDoubleClick=true
>
  <script type="text/javascript">
    const ${id} = {
        id: "${id}",
        element: null
    };
    const ${id}Enabled = ${preventDoubleClick?c};

    if (${id}Enabled) {
        window.addEventListener('load', ${id}Setup)
    }

    function ${id}Setup() {
        ${id}.element = document.getElementById(${id}.id);
        ${id}.element.addEventListener('click', disable${id});
    }

    function disable${id}() {
        ${id}.element.disabled = true;
    }
  </script>

  <button
    type="submit"
    class="${class}"
    id="${id}"
  >
    <#nested>
  </button>
</#macro>
