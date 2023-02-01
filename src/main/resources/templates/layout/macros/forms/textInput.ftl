<#import '/spring.ftl' as spring>

<#macro textInput
  path
  labelText
>
  <div class="text-input" style="width: 100%">
    <@spring.bind path/>
    <#assign errors = spring.status.errorMessages>
    <#assign errorClass = errors?has_content?then("has-errors", "")>

    <div style="margin-bottom: 20px">
      <label
        for="${path}"
        class="${errorClass}"
        style="width: 100%;"
      >
        ${labelText}
      </label>
    </div>
    <div>
      <input id="${path}"
        name="${spring.status.expression}"
        value="${spring.status.value!""}"
        class="${errorClass}"
        style="padding-left: 15px; padding-right: 15px; width: 100%; height: 3rem; margin-bottom: 20px; box-sizing: border-box"
      >
    </div>

    <#if errors?has_content>
      <div>
        <#list errors as error>
          <p class="error-hint">${error}</p>
        </#list>
      </div>
    </#if>
  </div>
</#macro>
